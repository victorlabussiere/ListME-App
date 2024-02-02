package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends RuntimeException implements ResponsesExceptions{

    private HttpStatus statusCode = HttpStatus.FORBIDDEN;
    private final String message = "Invalid password!";

    public InvalidPasswordException () {
        super();
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus status) {
        this.statusCode = status;
    }

    public String getMessage(){
        return this.message;
    }
}
