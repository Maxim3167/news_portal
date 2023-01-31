package com.dmdev.http.socket.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition","attachment; filename=\"filename.txt\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (OutputStream outputStream = resp.getOutputStream();
             InputStream inputStream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")){ //наш сервлет знает о класс-лоадере,который он загрузил,
            // а класс лоадер знает обо всех ресурсах, в том и числе и об first.json. Поэтом просто получаем стрим этого ресурса
            outputStream.write(inputStream.readAllBytes());
        }
    }
}
