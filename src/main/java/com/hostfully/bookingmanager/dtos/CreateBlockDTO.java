package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.controllers.validations.DateTimeRange;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@DateTimeRange
public record CreateBlockDTO(
        @NotNull(message = "startDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @NotNull(message = "endDate is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        @NotNull(message = "reason is mandatory")
        String reason,
        @NotNull(message = "propertyId is mandatory")
        UUID propertyId
) {
}
