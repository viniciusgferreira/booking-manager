package com.hostfully.bookingmanager.repositories;

import com.hostfully.bookingmanager.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByPropertyUidAndCheckOutDateAfterAndCheckInDateBefore(
            UUID propertyUid, LocalDate checkInDate, LocalDate checkOutDate);
}
