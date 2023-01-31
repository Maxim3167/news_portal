package myProject.exception;

import lombok.Getter;

public class CensorshipException extends RuntimeException{
    @Getter
    private final String VIOLATION = "The use of profanity is prohibited";
}
