package io.reflectoring.rentAcar.enums;

public enum InsuranceCategory {
    BASIC("BASIC"),
    STANDARD("STANDARD"),
    FULL_COVERAGE("FULL_COVERAGE"),
    COLLISION_DAMAGE_WAIVER("COLLISION_DAMAGE_WAIVER"),
    PERSONAL_ACCIDENT_INSURANCE("PERSONAL_ACCIDENT_INSURANCE"),
    PERSONAL_EFFECTS_COVERAGE("PERSONAL_EFFECTS_COVERAGE"),
    LIABILITY_INSURANCE("LIABILITY_INSURANCE"),
    SUPPLEMENTAL_LIABILITY_INSURANCE("SUPPLEMENTAL_LIABILITY_INSURANCE"),
    ROADSIDE_ASSISTANCE("ROADSIDE_ASSISTANCE"),
    UNINSURED_MOTORIST_PROTECTION("UNINSURED_MOTORIST_PROTECTION");

    private final String value;

    InsuranceCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

