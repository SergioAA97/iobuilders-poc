package com.picobankapi.poc.core.domain;

public abstract class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
