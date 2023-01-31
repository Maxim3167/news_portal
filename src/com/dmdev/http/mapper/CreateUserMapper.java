package com.dmdev.http.mapper;

import com.dmdev.http.web.dto.CreateUserDto;
import com.dmdev.http.web.model.Gender;
import com.dmdev.http.web.model.Role;
import com.dmdev.http.web.model.User;
import com.dmdev.http.web.util.DateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .image(IMAGE_FOLDER + userDto.getImage().getSubmittedFileName())// getSubmittedFileName возвращает просто название файла
                .birthday(DateFormatter.convertStringToDate(userDto.getBirthday()))
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.valueOf(userDto.getRole()))
                .gender(Gender.valueOf(userDto.getGender()))
                .build();
    }

    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
