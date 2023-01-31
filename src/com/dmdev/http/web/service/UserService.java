package com.dmdev.http.web.service;

import com.dmdev.http.web.dao.UserDao;
import com.dmdev.http.web.dto.CreateUserDto;
import com.dmdev.http.web.dto.UserDto;
import com.dmdev.http.web.model.User;
import com.dmdev.http.exception.ValidationException;
import com.dmdev.http.mapper.CreateUserMapper;
import com.dmdev.http.mapper.UserMapper;
import com.dmdev.http.validator.CreateUserValidator;
import com.dmdev.http.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getINSTANCE();
    private final UserMapper userMapper = UserMapper.getInstance();
    private static final String IMAGE_FOLDER = "users/";

    @SneakyThrows
    public Integer create(CreateUserDto userDto){
        //нам нужно провалидировать значение полученное из сервлета(получим оттуда dto)
        //если все ок, смапить нашу dto в User
        // и далее передать в дао слой
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrorList());
        }
        User user = createUserMapper.mapFrom(userDto);
        imageService.upload(user.getImage(),userDto.getImage().getInputStream());
        userDao.save(user);
        return user.getId();
    }

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email,password).map(userMapper::mapFrom);
    }

    public static UserService getInstance(){
        return INSTANCE;
    }
}
