package com.dmdev.http.mapper;

import com.dmdev.http.web.dto.UserDto;
import com.dmdev.http.web.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    public static UserMapper getInstance(){
        return INSTANCE;
    }
    @Override
    public UserDto mapFrom(User object) {
      return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .image(object.getImage())
                .gender(object.getGender())
                .role(object.getRole())
                .build();
    }
}
