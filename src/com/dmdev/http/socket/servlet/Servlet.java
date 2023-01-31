package com.dmdev.http.socket.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.stream.Stream;

@WebServlet("/first")
public class Servlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        resp.setContentType("text/html");
        resp.setHeader("personalHeader","It's my header");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        while (headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();
            System.out.println(nextElement);
        }
        try(PrintWriter writer = resp.getWriter()){
            writer.write("Привет из 1 сервлета");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader bufferedReader = req.getReader();
             Stream<String> lines = bufferedReader.lines()) {
            lines.forEach(System.out::println);
        }
    }
}
