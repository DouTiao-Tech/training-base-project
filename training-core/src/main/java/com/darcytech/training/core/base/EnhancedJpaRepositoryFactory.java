package com.darcytech.training.core.base;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;

class EnhancedJpaRepositoryFactory extends JpaRepositoryFactory {

    public EnhancedJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
        return new DelegatingQueryLookupStrategy(super.getQueryLookupStrategy(key, evaluationContextProvider));
    }

}
