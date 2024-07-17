package io.reflectoring.rentAcar.util;

public enum ErrorMessages {
    BRANCH_ADDRESS_NOT_FOUND("BRANCH_ADDRESS_NOT_FOUND"),
    BRANCH_NOT_FOUND("BRANCH_NOT_FOUND"),
    STAFF_NOT_FOUND("STAFF_NOT_FOUND");
    private final String value;
    ErrorMessages(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}