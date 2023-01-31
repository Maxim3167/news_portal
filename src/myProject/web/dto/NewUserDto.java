package myProject.web.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class NewUserDto {
    String name;
    String birthday;
    String country;
    String email;
    String password;
    String role;
    String gender;
}
