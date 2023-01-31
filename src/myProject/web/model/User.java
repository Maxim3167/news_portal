package myProject.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String country;
    private String email;
    private String password;
    private Boolean isBanned;
    private LocalDate banDate;
    private Integer notice;
    private Role role;
    private Gender gender;
}
