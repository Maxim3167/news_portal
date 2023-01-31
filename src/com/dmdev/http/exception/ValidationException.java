package com.dmdev.http.exception;

import com.dmdev.http.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errorList;

    public ValidationException(List<Error> errorList) {
        this.errorList = errorList;
    }
}
