package myProject.web.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class BirthdayFormatter {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate parse(String date){
        return LocalDate.parse(date, FORMATTER);
    }

    public static boolean isValidDate(String date){
        try {
            parse(date);
            return true;
        }
        catch (DateTimeParseException exception){
            return false;
        }
    }
}
