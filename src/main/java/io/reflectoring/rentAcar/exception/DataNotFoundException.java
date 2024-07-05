package io.reflectoring.rentAcar.exception;


public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}