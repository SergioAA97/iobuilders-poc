package com.picobankapi.poc.user.application.port.out;

public interface PasswordEncoder {
    String encode(String payload);

    Boolean equals(String payload, String hash);
}
