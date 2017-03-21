package com.darcytech.training.core.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

import static com.darcytech.training.core.base.Query2.padSize;

class DelegatingRepositoryQuery implements RepositoryQuery {

    private static final Logger logger = LoggerFactory.getLogger(DelegatingRepositoryQuery.class);

    private RepositoryQuery repositoryQuery;

    public DelegatingRepositoryQuery(RepositoryQuery repositoryQuery) {
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public Object execute(Object[] parameters) {
        if (requirePad(parameters)) {
            parameters = Arrays.copyOf(parameters, parameters.length);
            for (int i = 0; i < parameters.length; i++) {
                Object param = parameters[i];
                if (param instanceof Collection) {
                    parameters[i] = pad((Collection) param);
                }
            }
        }
        return repositoryQuery.execute(parameters);
    }

    private Object pad(Collection<?> param) {
        int paramSize = param.size();
        int padSize = padSize(paramSize);
        if (padSize == 0) {
            if (paramSize > Query2.MAX_IN_QUERY_SIZE) {
                logger.warn("cannot pad for param in query " + getQueryMethod().getName() + ", param size " + paramSize);
            }
            return param;
        }

        List<? super Object> result = new ArrayList<>(paramSize + padSize);
        result.addAll(param);

        Object last = result.get(paramSize - 1);
        for (int i = 0; i < padSize; i++) {
            result.add(last);
        }
        return result;
    }

    @Override
    public QueryMethod getQueryMethod() {
        return repositoryQuery.getQueryMethod();
    }

    private boolean requirePad(Object[] parameters) {
        for (Object param : parameters) {
            if (requirePad(param)) {
                return true;
            }
        }
        return false;
    }

    private boolean requirePad(Object param) {
        return param instanceof Collection && collectionPadSize((Collection) param) > 0;
    }

    private int collectionPadSize(Collection<?> param) {
        return padSize(param.size());
    }

}
