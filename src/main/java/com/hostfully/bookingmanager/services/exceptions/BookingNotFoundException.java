package com.hostfully.bookingmanager.services.exceptions;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message) {
        super(message);
    }

    public BookingNotFoundException() {
        super("Booking not found");
    }

}