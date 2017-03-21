package com.darcytech.training.core.base;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.compile.RenderingContext;
import org.hibernate.jpa.criteria.expression.LiteralExpression;

class LiteralExpression2<T> extends LiteralExpression<T> {

    private static final long serialVersionUID = -7814683748409327509L;

    public LiteralExpression2(CriteriaBuilderImpl criteriaBuilder, T literal) {
        super(criteriaBuilder, literal);
    }

    @Override
    public String render(RenderingContext renderingContext) {
        return ':' + renderingContext.registerLiteralParameterBinding(getLiteral(), getJavaType());
    }

}


