package myProject.web.dao;

import lombok.SneakyThrows;
import myProject.web.model.News;
import myProject.web.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsDao implements Dao<Integer, News> {
    private static final String FIND_NEWS = """
            select * from news_schema.news
            """;
    private static final String CREATE_NEWS = """
            insert into news_schema.news(name,create_date,text,author_id) values (?,?,?,?)
            """;
    private static final String FIND_BY_ID = """
            select * from news_schema.news where id = ?
            """;
    private static final String UPDATE_NAME_AND_CONTENT = """
            update news_schema.news set name = ?,text = ? where id = ?
            """;
    private static final String UPDATE_CONTENT = """
            update news_schema.news set text = ? where id = ?
            """;
    private static final String UPDATE_NAME = """
            update news_schema.news set name = ? where id = ?
            """;
    private static final NewsDao INSTANCE = new NewsDao();

    @SneakyThrows
    @Override
    public List<News> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_NEWS)) {
             ResultSet resultSet = preparedStatement.executeQuery();
             List<News> resultList = new ArrayList<>();
             while (resultSet.next()){
                 resultList.add(buildEntity(resultSet));
             }
             return resultList;
        }
    }

    @SneakyThrows
    private News buildEntity(ResultSet resultSet) {
        return News.builder()
                .id(resultSet.getObject(1,Integer.class))
                .name(resultSet.getObject(2,String.class))
                .createDate(resultSet.getObject(3, LocalDate.class))
                .text(resultSet.getObject(4,String.class))
                .authorId(resultSet.getObject(5,Integer.class))
                .build();
    }

    @Override
    @SneakyThrows
    public News create(News news) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEWS)) {
            preparedStatement.setObject(1,news.getName());
            preparedStatement.setObject(2,news.getCreateDate());
            preparedStatement.setObject(3,news.getText());
            preparedStatement.setObject(4,news.getAuthorId());
            preparedStatement.executeUpdate();
            return news;
        }
    }

    @Override
    public boolean update(String s) {
        return false;
    }

    @SneakyThrows
    public Optional<News> findById(String id){
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1,Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.ofNullable(buildEntity(resultSet));
            }
           return Optional.empty();
        }
    }
    @SneakyThrows
    public boolean updateContent(String content,String id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTENT)) {
            preparedStatement.setObject(1,content);
            preparedStatement.setObject(2,Integer.parseInt(id));
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public static NewsDao getInstance(){
        return INSTANCE;
    }
    @SneakyThrows
    public boolean updateAll(String newName, String newContent,String id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_AND_CONTENT)) {
            preparedStatement.setObject(1,newName);
            preparedStatement.setObject(2,newContent);
            preparedStatement.setObject(3,Integer.parseInt(id));
            return preparedStatement.executeUpdate() > 0;
        }
    }
    @SneakyThrows
    public boolean updateName(String newName,String id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME)) {
            preparedStatement.setObject(1,newName);
            preparedStatement.setObject(2,Integer.parseInt(id));
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
