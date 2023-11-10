package com.hostfully.bookingmanager.services.exceptions;

public class DateRangeNotAvailable extends RuntimeException{

    public DateRangeNotAvailable(String message) {
        super(message);
    }

    public DateRangeNotAvailable() {
        super("Date Range not available");
    }

}