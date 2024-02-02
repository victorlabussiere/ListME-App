package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException implements ResponsesExceptions{

    public UserNotFoundException() {
        super();
    }

    public HttpStatus getStatusCode() {
        return  HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return  "User not found";
    }
}
