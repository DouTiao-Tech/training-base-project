package com.darcytech.training.web.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter
     * (org.springframework.core.MethodParameter)
     */
    public boolean supportsParameter(MethodParameter parameter) {
        AuthenticatedUser annotation = parameter.getParameterAnnotation(AuthenticatedUser.class);
        if (annotation == null) {
            annotation = parameter.getClass().getAnnotation(AuthenticatedUser.class);
        }
        return annotation != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument
     * (org.springframework.core.MethodParameter,
     * org.springframework.web.method.support.ModelAndViewContainer,
     * org.springframework.web.context.request.NativeWebRequest,
     * org.springframework.web.bind.support.WebDataBinderFactory)
     */
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal != null && !parameter.getParameterType().isAssignableFrom(principal.getClass())) {
            throw new ClassCastException(principal + " is not assignable to " + parameter.getParameterType());
        }
        return principal;
    }

}