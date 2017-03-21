package com.darcytech.training.core.base;

import java.lang.reflect.Method;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

class DelegatingQueryLookupStrategy implements QueryLookupStrategy {

    private QueryLookupStrategy strategy;

    public DelegatingQueryLookupStrategy(QueryLookupStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        return new DelegatingRepositoryQuery(strategy.resolveQuery(method, metadata, factory, namedQueries));
    }

}
