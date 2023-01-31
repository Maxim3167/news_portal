package com.dmdev.http.web.dto;

import com.dmdev.http.web.model.Gender;
import com.dmdev.http.web.model.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}