package myProject.exception;

import lombok.Getter;

public class BanException extends RuntimeException{
    @Getter
    private final String text = "Sorry, your account is banned";
}
