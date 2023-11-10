package com.hostfully.bookingmanager.services;

import com.hostfully.bookingmanager.models.Block;
import com.hostfully.bookingmanager.models.Booking;
import com.hostfully.bookingmanager.repositories.BlockRepository;
import com.hostfully.bookingmanager.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DateRangeCheckerService {
    private final BookingRepository bookingRepository;
    private final BlockRepository blockRepository;

    @Autowired
    public DateRangeCheckerService(BookingRepository bookingRepository, BlockRepository blockRepository) {
        this.bookingRepository = bookingRepository;
        this.blockRepository = blockRepository;
    }

    public boolean isDateRangeNotAvailable(LocalDate start, LocalDate end, UUID propertyId) {
        return isBlocked(start, end, propertyId) || isBooked(start, end, propertyId);
    }

    private boolean isBlocked(LocalDate start, LocalDate end, UUID propertyId) {
        List<Block> existingBlocks = blockRepository.findByPropertyUidAndEndDateAfterAndStartDateBefore(
                propertyId,
                start,
                end
        );
        return !existingBlocks.isEmpty();
    }

    public boolean isBooked(LocalDate start, LocalDate end, UUID propertyId) {
        List<Booking> existingBookings = bookingRepository.findByPropertyUidAndCheckOutDateAfterAndCheckInDateBefore(
                propertyId,
                start,
                end
        );

        return !existingBookings.isEmpty();
    }
}
