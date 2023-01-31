package myProject.validator;

import myProject.web.dto.NewUserDto;
import myProject.web.util.BirthdayFormatter;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Optional;

public class NewUserValidator implements Validator<NewUserDto,NewUserValidationResult>{
    private static final NewUserValidator INSTANCE = new NewUserValidator();

    @Override
    public NewUserValidationResult isValidComment(NewUserDto userDto) {
        return null;
    }

    @Override
    public NewUserValidationResult isValidUser(NewUserDto newUserDto) {
        NewUserValidationResult validationResult = new NewUserValidationResult();
        String email = newUserDto.getEmail();
        if(!email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            validationResult.addError(Error.of("Your email is invalid"));
        }
        if(!BirthdayFormatter.isValidDate(newUserDto.getBirthday())){
            validationResult.addError(Error.of("Your birthday is invalid"));
        }
        LocalDate birthday = BirthdayFormatter.parse(newUserDto.getBirthday());
        Period period = Period.between(birthday, LocalDate.now());
        if(period.getYears() < 18){
            validationResult.addError(Error.of("Your age is less than 18"));
        }
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{6,20}$";
        if(!newUserDto.getPassword().matches(regex)){
            validationResult.addError(Error.of("The password must contain a digit, a capital letter, a special character and be no shorter than 6 characters"));
        }
        return validationResult;
    }

    public static NewUserValidator getInstance(){
        return INSTANCE;
    }
}
