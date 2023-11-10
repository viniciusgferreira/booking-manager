package com.hostfully.bookingmanager.services.exceptions;

public class GuestNotFoundException extends RuntimeException{

    public GuestNotFoundException(String message) {
        super(message);
    }

    public GuestNotFoundException() {
        super("Guest not found");
    }

}