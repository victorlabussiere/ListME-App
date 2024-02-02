package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public class EmailInUseException extends RuntimeException implements ResponsesExceptions{

    public EmailInUseException() {
        super();
    }

    @Override
    public String getMessage() {
        return "E-mail already in use exception";
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
