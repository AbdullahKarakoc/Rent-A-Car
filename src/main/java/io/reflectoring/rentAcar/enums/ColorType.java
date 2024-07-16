package io.reflectoring.rentAcar.enums;

public enum ColorType {
    RED("RED"),
    BLUE("BLUE"),
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    WHITE("WHITE"),
    BLACK("BLACK"),
    ORANGE("ORANGE"),
    PURPLE("PURPLE"),
    PINK("PINK"),
    GRAY("GRAY");

    private final String value;

    ColorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
