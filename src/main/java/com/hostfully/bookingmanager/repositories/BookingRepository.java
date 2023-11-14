package com.hostfully.bookingmanager.repositories;

import com.hostfully.bookingmanager.models.Booking;
import com.hostfully.bookingmanager.models.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByPropertyUidAndCheckOutDateAfterAndCheckInDateBefore(
            UUID propertyUid, LocalDate checkInDate, LocalDate checkOutDate);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.property.uid = :propertyId " +
            "AND b.checkOutDate > :start " +
            "AND b.checkInDate < :end " +
            "AND b.status = 'CONFIRMED'")
    List<Booking> isBooked(@Param("start") LocalDate start,
                     @Param("end") LocalDate end,
                     @Param("propertyId") UUID propertyId);

    @Modifying
    @Query("UPDATE Booking b SET b.status = :status WHERE b.uid = :uid")
    void updateBookingStatus(@Param("uid") UUID uid, @Param("status") BookingStatus status);


}
