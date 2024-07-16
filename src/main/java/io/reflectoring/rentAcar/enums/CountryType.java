package io.reflectoring.rentAcar.enums;

public enum CountryType {

    TURKEY("TURKEY"),
    AMERICA("AMERICA"),
    ENGLAND("ENGLAND"),
    DEUTCHLAND("DEUTCHLAND"),
    FRANCE("FRANCE"),
    ITALY("ITALY");

    private final String value;

    CountryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}