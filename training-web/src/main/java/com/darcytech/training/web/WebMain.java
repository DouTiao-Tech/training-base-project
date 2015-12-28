package com.darcytech.training.web;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class WebMain {

    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }

    @Bean
    public FilterRegistrationBean etagFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ShallowEtagHeaderFilter());
        registration.setName("etagFilter");
        return registration;
    }

    @Bean
    public ServletRegistrationBean passwordServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean();
        registration.setUrlMappings(Collections.singletonList("/password"));
        registration.setServlet(new PasswordServlet());
        registration.setName("password");
        return registration;
    }

}
