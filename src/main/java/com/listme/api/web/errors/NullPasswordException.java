package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public class NullPasswordException extends RuntimeException implements ResponsesExceptions{
    String message = "No data arrived from request";
    HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public NullPasswordException(){
        super();
    }

    public NullPasswordException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
