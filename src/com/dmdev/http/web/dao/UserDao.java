package com.dmdev.http.web.dao;

import com.dmdev.http.web.model.Gender;
import com.dmdev.http.web.model.Role;
import com.dmdev.http.web.model.User;
import com.dmdev.http.web.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User>{
    private static final UserDao INSTANCE = new UserDao();

    public static final String SAVE_ENTITY = """
            insert into users(name, birthday, email, password, role, gender,image) VALUES (?,?,?,?,?,?,?)
            """;
    public static final String SELECT_BY_EMAIL_AND_PASSWORD = """
            select * from users where email = ? and password = ?
            """;
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email,String password){
        try(Connection connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD)){
            preparedStatement.setObject(1,email);
            preparedStatement.setObject(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if(resultSet.next()){
               user = buildEntity(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ENTITY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,entity.getName());
            preparedStatement.setObject(2,entity.getBirthday());
            preparedStatement.setObject(3,entity.getEmail());
            preparedStatement.setObject(4,entity.getPassword());
            preparedStatement.setObject(5,entity.getRole().name());
            preparedStatement.setObject(6,entity.getGender().name());
            preparedStatement.setObject(7,entity.getImage());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id",Integer.class));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private User buildEntity(ResultSet resultSet){
      return User.builder()
                .id(resultSet.getObject("id",Integer.class))
                .name(resultSet.getObject("name",String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email",String.class))
                .image(resultSet.getObject("image",String.class))
                .password(resultSet.getObject("password",String.class))
                .role(Role.valueOf(resultSet.getObject("role",String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender",String.class)))
                .build();
    }

    public static UserDao getInstance(){
        return INSTANCE;
    }
}
