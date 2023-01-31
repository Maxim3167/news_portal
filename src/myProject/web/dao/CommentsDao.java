package myProject.web.dao;

import lombok.SneakyThrows;
import myProject.web.model.Comments;
import myProject.web.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CommentsDao implements Dao<Integer, Comments> {
    private static final CommentsDao INSTANCE = new CommentsDao();
    private static final String CREATE_COMMENT = """
            insert into news_repository.news_schema.comments (text, author_id, news_id) values (?,?,?);
            """;

    @Override
    public List<Comments> findAll() {
        return null;
    }
    @SneakyThrows
    @Override
    public Comments create(Comments comments) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)) {
             preparedStatement.setObject(1,comments.getText());
             preparedStatement.setObject(2,comments.getAuthorId());
             preparedStatement.setObject(3,comments.getNewsId());
             preparedStatement.executeUpdate();
             return comments;
        }
    }

    @Override
    public boolean update(String s) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public static CommentsDao getInstance(){
        return INSTANCE;
    }
}
