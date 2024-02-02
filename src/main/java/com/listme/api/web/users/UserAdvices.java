package com.listme.api.web.users;

import com.listme.api.web.errors.EmailInUseException;
import com.listme.api.web.errors.InvalidPasswordException;
import com.listme.api.web.errors.NullPasswordException;
import com.listme.api.web.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAdvices {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException err) {
        return err.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NullPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public String nullPasswordHandler(UserNotFoundException err) {
        return err.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailInUseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String emailInUseHandler(UserNotFoundException err) {
        return err.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String invalidPasswordHandler (InvalidPasswordException err) {
        return err.getMessage();
    }

}
