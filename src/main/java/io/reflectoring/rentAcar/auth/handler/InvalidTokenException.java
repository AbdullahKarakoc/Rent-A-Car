package io.reflectoring.rentAcar.auth.handler;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
