package myProject.web.service;

import myProject.exception.DuplicateException;
import myProject.exception.ValidationException;
import myProject.mapper.NewUserDtoMapper;
import myProject.validator.NewUserValidationResult;
import myProject.validator.NewUserValidator;
import myProject.web.dao.UserDao;
import myProject.web.dto.NewUserDto;
import myProject.web.model.User;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final NewUserDtoMapper newUserDtoMapper = NewUserDtoMapper.getInstance();
    private final NewUserValidator newUserValidator = NewUserValidator.getInstance();

    public Optional<User> login(String email, String password){
       return userDao.findByEmailAndPassword(email,password);
    }

    public static UserService getInstance(){
        return INSTANCE;
    }

    public void save(NewUserDto newUserDto) {
        //необходимо провалидировать полученное значение
        //смапить в User
        //передать в дао
        NewUserValidationResult validationResult = newUserValidator.isValid(newUserDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrorList());
        }
        User user = newUserDtoMapper.mapToEntity(newUserDto);
        if(userDao.findByEmailAndPassword(newUserDto.getEmail(), newUserDto.getPassword()).isPresent()){
            throw new DuplicateException();
        }
        userDao.save(user);

    }
}
