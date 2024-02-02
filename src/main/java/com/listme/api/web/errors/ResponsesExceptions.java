package com.listme.api.web.errors;

import org.springframework.http.HttpStatus;

public interface ResponsesExceptions {
     public String getMessage();
     public HttpStatus getStatusCode();
}
