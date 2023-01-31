package com.dmdev.http.web.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
   private Integer id;
   private String name;
   private String image;
   private LocalDate birthday;
   private String email;
   private String password;
   private Role role;
   private Gender gender;




}
