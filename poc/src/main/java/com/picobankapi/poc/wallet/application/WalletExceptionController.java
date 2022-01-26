package com.picobankapi.poc.wallet.application;

import javax.servlet.http.HttpServletRequest;

import com.picobankapi.poc.core.application.ApiErrorController;
import com.picobankapi.poc.wallet.domain.WalletException;
import com.picobankapi.poc.wallet.domain.WalletNotEnoughFundsException;
import com.picobankapi.poc.wallet.domain.WalletNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WalletExceptionController extends ApiErrorController {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleError(status, ex);
    }

    @ExceptionHandler(value = WalletNotEnoughFundsException.class)
    public @ResponseBody ResponseEntity<Object> handleUserAlreadyExists(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(value = WalletNotFoundException.class)
    public @ResponseBody ResponseEntity<Object> handleUserNotFound(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = WalletException.class)
    public @ResponseBody ResponseEntity<Object> handleUserError(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
