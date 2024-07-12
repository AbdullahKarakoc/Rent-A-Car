package io.reflectoring.rentAcar.enums;

public enum PaymentType {

    CREDIT_CARD("CREDIT_CARD"),
    CASH("CASH"),
    BANK_TRANSFER("BANK_TRANSFER"),
    CHECK("CHECK"),
    MEAL_CARD("MEAL_CARD"),
    GIFT_CARD("GIFT_CARD"),
    POINTS("POINTS"),
    OTHER("OTHER");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
