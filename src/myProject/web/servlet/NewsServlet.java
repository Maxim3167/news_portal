package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.web.dto.NewsDto;
import myProject.web.model.User;
import myProject.web.service.NewsService;
import myProject.web.util.JspHelp;

import java.io.IOException;
import java.util.List;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    private final NewsService newsService = NewsService.getInstance();
    private static final NewsServlet INSTANCE = new NewsServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsDto> allNews = newsService.findAllNews();
        req.setAttribute("newsDtoList",allNews);
        req.getRequestDispatcher(JspHelp.getPath("news")).forward(req,resp);
    }

    public static NewsServlet getInstance(){
        return INSTANCE;
    }
}
