package com.picobankapi.poc.wallet.application;

import javax.annotation.Priority;
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

@ControllerAdvice
@Order(0)
@Priority(1)
public class WalletExceptionController extends ApiErrorController {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleError(status, ex);
    }

    @ExceptionHandler(WalletNotEnoughFundsException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public @ResponseBody ResponseEntity<Object> handleWalletNotEnoguhFunds(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public @ResponseBody ResponseEntity<Object> handleWalletNotFound(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = WalletException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public @ResponseBody ResponseEntity<Object> handleWalletError(final Exception ex,
            final HttpServletRequest request) {
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
