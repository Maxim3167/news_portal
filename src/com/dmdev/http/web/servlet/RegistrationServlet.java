package com.dmdev.http.web.servlet;

import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.web.dto.CreateUserDto;
import com.dmdev.http.web.model.Gender;
import com.dmdev.http.web.model.Role;
import com.dmdev.http.web.service.UserService;
import com.dmdev.http.web.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part image = req.getPart("image");
        CreateUserDto userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .image(image)
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();
        try{
            userService.create(userDto);
            resp.sendRedirect("/login");
        }
        catch (ValidationException e){
            req.setAttribute("errors",e.getErrorList());
            doGet(req,resp);
        }

    }
//важно понимать, что doGet вызывается первым при запросе на этот сервлет
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles",Role.values());
        req.setAttribute("genders", Gender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req,resp);
    }
}
