package com.hostfully.bookingmanager.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record BlockResponseDTO(
        UUID uid,
        LocalDate startDate,
        LocalDate endDate,
        String reason,
        PropertyResponseDTO property
) {
}
