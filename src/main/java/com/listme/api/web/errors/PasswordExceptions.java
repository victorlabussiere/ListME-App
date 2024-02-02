package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public class PasswordExceptions extends RuntimeException implements ResponsesExceptions{
    String message = "Any password has been passed";
    HttpStatus statusCode = HttpStatus.NOT_MODIFIED;

    public PasswordExceptions(){
        super();
    }

    public PasswordExceptions(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return "Any password has been passed";
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public static void validatePassword(boolean valid) {
        if (!valid) {
            throw new PasswordExceptions("Passwords doesn't matches", HttpStatus.FORBIDDEN);
        }
    }
}
