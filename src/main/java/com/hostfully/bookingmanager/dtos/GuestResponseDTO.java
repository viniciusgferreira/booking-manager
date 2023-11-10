package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.models.Guest;

import java.util.UUID;

public record GuestResponseDTO(
         UUID uid,
         String firstName,
         String lastName

) {
    public GuestResponseDTO(Guest entity) {
        this(entity.getUid(), entity.getFirstName(), entity.getLastName());
    }
}
