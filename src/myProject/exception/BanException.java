package myProject.exception;

import lombok.Getter;

public class BanException extends RuntimeException{
    private final String errorText;

    public BanException(String errorText){
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
