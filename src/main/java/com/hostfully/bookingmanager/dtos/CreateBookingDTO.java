package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.controllers.validations.DateTimeRange;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@DateTimeRange
public record CreateBookingDTO(
        @NotNull(message = "checkInDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate checkInDate,
        @NotNull(message = "checkOutDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate checkOutDate,
        @NotNull(message = "guestNumber is mandatory")
        Integer guestNumber,
        @NotNull(message = "guestId is mandatory")
        UUID guestId,
        @NotNull(message = "propertyId is mandatory")
        UUID propertyId,
        String status
) {
}
