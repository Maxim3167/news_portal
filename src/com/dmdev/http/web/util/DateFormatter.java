package com.dmdev.http.web.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class DateFormatter {
     private static final String pattern = "yyyy-MM-dd";
     private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

     public static LocalDate convertStringToDate(String date){
         return LocalDate.parse(date,formatter);
     }

     public boolean isValid(String date){
         try {
             convertStringToDate(date);
             return true;
         }
         catch (DateTimeParseException exception){
             return false;
         }
     }
}
