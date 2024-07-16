package io.reflectoring.rentAcar.enums;

public enum BrandType {

    AUDI("AUDI"),
    BMW("BMW"),
    FORD("FORD"),
    HONDA("HONDA"),
    HYUNDAI("HYUNDAI"),
    KIA("KIA"),
    MERCEDES("MERCEDES"),
    TESLA("TESLA"),
    TOYOTA("TOYOTA"),
    VOLVO("VOLVO"),
    OTHER("OTHER");

    private final String value;

    BrandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
