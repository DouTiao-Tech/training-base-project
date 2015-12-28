package com.darcytech.training.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordServlet extends HttpServlet {

    public static final String DEFAULT_LANGUAGE = "EN-US";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acceptLanguage = req.getHeader("Accept-Language");
        if (acceptLanguage == null) {
            acceptLanguage = DEFAULT_LANGUAGE;
        } else {
            acceptLanguage = acceptLanguage.toUpperCase();
        }
        PrintWriter writer = resp.getWriter();
        if (acceptLanguage.contains(DEFAULT_LANGUAGE)) {
            writer.write("this is an english response");
        } else {
            writer.write("这是一个回复的内容");
        }
    }

}
