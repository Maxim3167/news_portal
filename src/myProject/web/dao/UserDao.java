package myProject.web.dao;

import lombok.SneakyThrows;
import myProject.web.model.Gender;
import myProject.web.model.Role;
import myProject.web.model.User;
import myProject.web.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            select * from news_repository.news_schema.users where email = ? and password = ? 
            """;
    private static final String SAVE_USER = """
            insert into news_repository.news_schema.users (name, birthday, country, email, password, role, gender,isbanned,notice)
             VALUES (?,?,?,?,?,?,?,?,?)
            """;
    private static final String FIND_USER_BY_ID = """
            select * from news_repository.news_schema.users where id = ?
            """;
    private static final String SET_NOTICE = """
            update news_repository.news_schema.users set notice = ? where id = ? 
            """;
    private static final String SET_IS_BANNED = """
            update news_repository.news_schema.users set isbanned = ? where id = ?
            """;
    private static final String SET_BAN_DATE = """
            update news_repository.news_schema.users set ban_date = ? where id = ?
            """;

    private static final String GET_USER_STATUS = """
            select isbanned from news_repository.news_schema.users where id = ?
            """;
    private static final String GET_USER_NOTICE = """
            select notice from news_repository.news_schema.users where id = ?
            """;
    private static final String SET_TURN_OFF_BAN_DATE = """
            update news_repository.news_schema.users set turn_off_ban_date = ? where id = ?
            """;
    private static final String GET_TURN_OFF_BAN = """
            select turn_off_ban_date from news_repository.news_schema.users where id = ?
            """;
    private static final String GET_BAN_DATE = """
            select ban_date from news_repository.news_schema.users where id = ?
            """;

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email,String password){
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {
             preparedStatement.setObject(1,email);
             preparedStatement.setObject(2,password);
             ResultSet resultSet = preparedStatement.executeQuery();
             if(resultSet.next()){
                 return Optional.of(buildEntity(resultSet));
             }
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(String s) {
        return false;
    }

    @SneakyThrows
    @Override
    public Optional<User> findById(Integer id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntity(resultSet));
            }
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @SneakyThrows
    @Override
    public User create(User entity) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1,entity.getName());
            preparedStatement.setObject(2,entity.getBirthday());
            preparedStatement.setObject(3,entity.getCountry());
            preparedStatement.setObject(4,entity.getEmail());
            preparedStatement.setObject(5,entity.getPassword());
            preparedStatement.setObject(6,entity.getRole().name());
            preparedStatement.setObject(7,entity.getGender().name());
            preparedStatement.setObject(8,entity.getIsBanned());
            preparedStatement.setObject(9,entity.getNotice());
            preparedStatement.executeUpdate();
            return entity;
        }
    }

    @SneakyThrows
    public void setNotice(Integer id, boolean change){
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SET_NOTICE)) {
            preparedStatement.setObject(1,change);
            preparedStatement.setObject(2,id);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public void setIsBanned(Integer id, boolean change){
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SET_IS_BANNED)) {
            preparedStatement.setObject(1,change);
            preparedStatement.setObject(2,id);
            preparedStatement.executeUpdate();
        }
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }

    @SneakyThrows
    private User buildEntity(ResultSet resultSet){
        return User.builder()
                .id(resultSet.getObject("id",Integer.class))
                .name(resultSet.getObject("name",String.class))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .isBanned(resultSet.getObject("isbanned",Boolean.class))
                .banDate(resultSet.getObject("ban_date", LocalDateTime.class))
                .notice(resultSet.getObject("notice",Boolean.class))
                .country(resultSet.getObject("country",String.class))
                .email(resultSet.getObject("email",String.class))
                .password(resultSet.getObject("password",String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender",String.class)))
                .build();
    }

    @SneakyThrows
    public void setBanDate(Integer id, LocalDateTime now) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SET_BAN_DATE)) {
            preparedStatement.setObject(1,now);
            preparedStatement.setObject(2,id);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public boolean getIsBanned(Integer id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_STATUS)) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getObject("isbanned",Boolean.class);
            }
            return false;
        }
    }
    @SneakyThrows
    public boolean getNotice(Integer id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_NOTICE)) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getObject("notice",Boolean.class);
            }
            return false;
        }

    }
    @SneakyThrows
    public void setTurnOffBanDate(Integer id, LocalDateTime dateTime) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SET_TURN_OFF_BAN_DATE)) {
            preparedStatement.setObject(1,dateTime);
            preparedStatement.setObject(2,id);
            preparedStatement.executeUpdate();
        }
    }
    @SneakyThrows
    public LocalDate getTurnOffBanDate(Integer id) {
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TURN_OFF_BAN)) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDate("turn_off_ban_date").toLocalDate();
            }
            return null;
        }
    }
    @SneakyThrows
    public LocalDate getBanDate(Integer id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BAN_DATE)) {
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getDate("ban_date").toLocalDate();
            }
            return null;
        }
    }

}
