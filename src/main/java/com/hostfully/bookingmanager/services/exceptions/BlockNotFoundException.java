package com.hostfully.bookingmanager.services.exceptions;

public class BlockNotFoundException extends RuntimeException{

    public BlockNotFoundException(String message) {
        super(message);
    }

    public BlockNotFoundException() {
        super("Block not found");
    }

}