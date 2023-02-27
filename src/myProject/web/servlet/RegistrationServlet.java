package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.exception.DuplicateException;
import myProject.exception.ValidationException;
import myProject.web.dto.NewUserDto;
import myProject.web.service.UserService;
import myProject.web.util.JspHelp;
import myProject.web.util.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final RegistrationServlet INSTANCE = new RegistrationServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewUserDto newUserDto = NewUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .country(req.getParameter("country"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();
        try {
            userService.save(newUserDto);
            resp.sendRedirect("/myLogin");
        }
        catch (ValidationException exception ){
            req.setAttribute("errors",exception.getErrors());
            req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
        }
        catch (DuplicateException e){
            req.setAttribute("duplicate",e);
            req.getRequestDispatcher(JspHelp.getPath("registration")).forward(req,resp);
        }
    }

    public static RegistrationServlet getInstance(){
        return INSTANCE;
    }
}
