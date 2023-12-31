package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.models.BookingStatus;

import java.time.LocalDate;
import java.util.UUID;

public record BookingResponseDTO(
        UUID uid,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        Integer guestNumber,
        GuestResponseDTO guest,
        PropertyResponseDTO property,
        BookingStatus status
) {
}
