package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.controllers.validations.DateTimeRange;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@DateTimeRange
public record RebookDTO(
        @NotNull(message = "checkInDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate checkInDate,
        @NotNull(message = "checkOutDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate checkOutDate

) {
}
