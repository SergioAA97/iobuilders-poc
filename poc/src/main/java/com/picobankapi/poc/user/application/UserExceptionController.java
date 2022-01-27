package com.picobankapi.poc.user.application;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import com.picobankapi.poc.core.application.ApiErrorController;
import com.picobankapi.poc.user.domain.UserAlreadyExistsException;
import com.picobankapi.poc.user.domain.UserException;
import com.picobankapi.poc.user.domain.UserInvalidException;
import com.picobankapi.poc.user.domain.UserNotFoundException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(-1)
@ControllerAdvice
public class UserExceptionController extends ApiErrorController {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public @ResponseBody ResponseEntity<Object> handleUserAlreadyExists(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public @ResponseBody ResponseEntity<Object> handleUserNotFound(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = {UserInvalidException.class, ConstraintViolationException.class})
    public @ResponseBody ResponseEntity<Object> handleUserInvalid(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(value = UserException.class)
    public @ResponseBody ResponseEntity<Object> handleUserError(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
