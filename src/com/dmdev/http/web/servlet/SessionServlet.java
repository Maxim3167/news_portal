package com.dmdev.http.web.servlet;

import com.dmdev.http.web.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // получает сессию по JSESSIONID, а если эта кука не пришла, то создает эту
        // сессию и отправляет на сторону клиента куку JSESSIONID
        Object user = (UserDto) session.getAttribute(USER);
        if(user == null){
            user = UserDto.builder().id(25).email("test@gmail.com").build();
            session.setAttribute(USER,user); // метод который позволяет сохранять на сервер любую информацию о данной сессии
            // когда к нам снова в этой же сессии прийдет http запрос при помощи cookie JSESSIONID мы сможем
            // идентифицировать данную сессию и уже что-то знать о пользователе, к примеру
        }
        req.getRequestDispatcher("flights").forward(req,resp);
        PrintWriter respWriter = resp.getWriter();
        respWriter.write("AfterForward");
        respWriter.close();
    }
}
