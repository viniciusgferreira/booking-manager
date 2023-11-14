package com.hostfully.bookingmanager.models;

public enum BookingStatus {
    CONFIRMED("confirmed"),
    REBOOKED("rebooked"),
    CANCELLED("cancelled");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BookingStatus fromValue(String value) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid BookingStatus: " + value);
    }
}
