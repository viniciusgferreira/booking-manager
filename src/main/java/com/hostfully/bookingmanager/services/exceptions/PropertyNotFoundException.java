package com.hostfully.bookingmanager.services.exceptions;

public class PropertyNotFoundException extends RuntimeException{

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException() {
        super("Property not found");
    }

}