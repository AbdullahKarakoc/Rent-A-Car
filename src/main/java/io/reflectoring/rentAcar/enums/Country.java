package io.reflectoring.rentAcar.enums;

public enum Country {

    TURKEY("TURKEY"),
    AMERICA("AMERICA"),
    ENGLAND("ENGLAND"),
    DEUTCHLAND("DEUTCHLAND"),
    FRANCE("FRANCE"),
    ITALY("ITALY");

    private final String value;

    Country(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}