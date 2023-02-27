package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.exception.BanException;
import myProject.exception.CensorshipException;
import myProject.web.dto.NewsDto;
import myProject.web.model.User;
import myProject.web.service.NewsService;
import myProject.web.util.JspHelp;

import java.io.IOException;

@WebServlet("/createNews")
public class CreateNewsServlet extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance();
    private static final CreateNewsServlet INSTANCE = new CreateNewsServlet();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        NewsDto newsDto = NewsDto.builder()
                .name(req.getParameter("name"))
                .text(req.getParameter("text"))
                .authorId(user.getId())
                .build();

        try {
            newsService.create(newsDto);
        }
        catch (BanException | CensorshipException e){
            req.setAttribute("error",e);
        }
        //resp.sendRedirect("/news");
        req.getRequestDispatcher(JspHelp.getPath("news")).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelp.getPath("createNews")).forward(req,resp);
    }

    public static CreateNewsServlet getInstance(){
        return INSTANCE;
    }
}
