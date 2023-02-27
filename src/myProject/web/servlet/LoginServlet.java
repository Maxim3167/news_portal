package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import myProject.validator.Error;
import myProject.web.model.User;
import myProject.web.service.UserService;
import myProject.web.util.JspHelp;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/myLogin")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final LoginServlet INSTANCE = new LoginServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelp.getPath("login")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = userService.login(req.getParameter("Email"), req.getParameter("Password"));
        user.ifPresentOrElse(user1 -> onLoginSuccess(user1,req,resp),
                ()-> onLoginFailed(req,resp));
    }

    @SneakyThrows
    private void onLoginFailed(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(401);
        resp.sendRedirect("/myLogin?error=EmailOrPasswordIsNotCorrect");
    }

    @SneakyThrows
    private void onLoginSuccess(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/news");
    }


    public static LoginServlet getInstance(){
        return INSTANCE;
    }
}
