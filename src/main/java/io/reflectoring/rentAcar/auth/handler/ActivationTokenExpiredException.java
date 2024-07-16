package io.reflectoring.rentAcar.auth.handler;

public class ActivationTokenExpiredException extends RuntimeException {
    public ActivationTokenExpiredException(String message) {
        super(message);
    }
}
