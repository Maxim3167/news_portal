package myProject.exception;

import lombok.Getter;

public class CensorshipException extends RuntimeException{

    private final String errorText;

    public CensorshipException(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
