package com.picobankapi.poc.core.application;

import javax.annotation.Priority;

import com.picobankapi.poc.core.domain.ApiError;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(-2)
@Priority(2)
public class ApiErrorController extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    protected @ResponseBody ResponseEntity<Object> handleError(final HttpStatus status, final Exception ex) {
        ApiError apiError = new ApiError(status, ex.getLocalizedMessage());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return handleError(status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleError(status, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return handleError(status, ex);
    }
}
