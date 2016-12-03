package com.darcytech.training.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.darcytech.training.core.TrainingException;
import com.google.common.collect.ImmutableMap;

/**
 * 这是利用 ControllerAdvice 和 ExceptionHandler来为全局所有 Controller 做异常处理。
 * 另外一种做法是写一个 AbstractController,然后在里面提供一个 ExceptionHandler 方法;
 * 第三种做法是这边不做任何处理,让 tomcat 的 errorPage 来处理,整体来看,这种做法更符合我们的精神,异常一概往外抛,除非自己能处理;
 */
@ControllerAdvice
public class TrainingExceptionHandlerAdvice {

    @ExceptionHandler({TrainingException.class})
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ImmutableMap.of("errorMessage", ex.getMessage()));
    }

}
