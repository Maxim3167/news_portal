package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.web.service.NewsService;
import myProject.web.util.JspHelp;

import java.io.IOException;

@WebServlet("/updateNews")
public class UpdateNewsServlet extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance();
    private static final UpdateNewsServlet INSTANCE = new UpdateNewsServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id",req.getParameter("newsId"));
        req.getRequestDispatcher(JspHelp.getPath("updateNews")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        newsService.update(req.getParameter("newName"),req.getParameter("newContent"),req.getParameter("newsId"));
        resp.sendRedirect("/news");
    }

    public static UpdateNewsServlet getInstance(){
        return INSTANCE;
    }
}
