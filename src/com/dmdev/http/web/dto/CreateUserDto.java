package com.dmdev.http.web.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
//все dto должны быть immutable
public class CreateUserDto {
    String name;
    String birthday;
    Part image;
    String email;
    String password;
    String role;
    String gender;
}
