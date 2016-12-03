package com.darcytech.training.web;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

public class RestTemplateMain {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            Object o = template.execute("http://www.baidu.com", HttpMethod.GET, request -> {
                    },
                    ClientHttpResponse::getStatusCode);
            System.out.printf(o.toString());
        }
    }

}
