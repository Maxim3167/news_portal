package myProject.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myProject.exception.BanException;
import myProject.exception.CensorshipException;
import myProject.web.dto.CommentsDto;
import myProject.web.model.User;
import myProject.web.service.CommentsService;
import myProject.web.util.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.CREATE_COMMENT)
public class CreateCommentsServlet extends HttpServlet {
    private static final CreateCommentsServlet INSTANCE = new CreateCommentsServlet();
    private final CommentsService commentsService = CommentsService.getInstance();


    public static CreateCommentsServlet getInstance(){
        return INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String comment = req.getParameter("comment");
        CommentsDto commentsDto = CommentsDto.builder()
                .text(comment)
                .newsId(req.getParameter("newsId"))
                .authorId(user.getId())
                .build();
        try {
            commentsService.create(commentsDto);
        }
        catch (BanException | CensorshipException e){
            req.setAttribute("error",e);
        }
        req.setAttribute("newsId",commentsDto.getNewsId());
        req.getRequestDispatcher("/newsText/*").forward(req,resp);

    }
}
