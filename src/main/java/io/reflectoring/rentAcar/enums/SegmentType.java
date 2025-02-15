package io.reflectoring.rentAcar.enums;

public enum SegmentType {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    SUV("SUV"),
    MPV("MPV"),
    PICKUP("PICKUP"),
    COUPE("COUPE"),
    CABRIO("CABRIO");

    private final String value;

    SegmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
