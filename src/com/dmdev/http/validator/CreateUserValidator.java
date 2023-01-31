package com.dmdev.http.validator;

import com.dmdev.http.web.dto.CreateUserDto;
import com.dmdev.http.web.model.Gender;
import com.dmdev.http.web.model.Role;
import com.dmdev.http.web.util.DateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
        ValidationResult resultUserValidator = new ValidationResult();
        if(userDto.getRole() == null || userDto.getGender() == null || Gender.valueOf(userDto.getGender()) == null || Role.valueOf(userDto.getRole()) == null){
            resultUserValidator.addError(Error.of("invalid.gender","Gender is invalid"));
        }
        if(!DateFormatter.isValid(userDto.getBirthday())){
            resultUserValidator.addError(Error.of("invalid.date","Date is invalid"));
        }
        return resultUserValidator;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}
