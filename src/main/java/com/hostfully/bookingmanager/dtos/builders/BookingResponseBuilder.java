package com.hostfully.bookingmanager.dtos.builders;

import com.hostfully.bookingmanager.dtos.BookingResponseDTO;
import com.hostfully.bookingmanager.dtos.GuestResponseDTO;
import com.hostfully.bookingmanager.dtos.PropertyResponseDTO;
import com.hostfully.bookingmanager.models.Booking;
import com.hostfully.bookingmanager.models.BookingStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class BookingResponseBuilder {
    private UUID uid;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer guestNumber;
    private GuestResponseDTO guest;
    private PropertyResponseDTO property;
    private BookingStatus status;

    public BookingResponseBuilder withId(UUID uid) {
        this.uid = uid;
        return this;
    }

    public BookingResponseBuilder withCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }
    public BookingResponseBuilder withCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
        return this;
    }
    public BookingResponseBuilder withGuestNumber(Integer guestNumber) {
        this.guestNumber = guestNumber;
        return this;
    }
    public BookingResponseBuilder withGuest(GuestResponseDTO guest) {
        this.guest = guest;
        return this;
    }

    public BookingResponseBuilder withProperty(PropertyResponseDTO property) {
        this.property = property;
        return this;
    }

    public BookingResponseBuilder withStatus(BookingStatus status) {
        this.status = status;
        return this;
    }

    public BookingResponseDTO build() {
        return  new BookingResponseDTO(uid, checkInDate, checkOutDate, guestNumber, guest, property, status);
    }

    public BookingResponseBuilder fromEntity(Booking bookingEntity) {
        return new BookingResponseBuilder().withId(bookingEntity.getUid())
                .withCheckInDate(bookingEntity.getCheckInDate())
                .withCheckOutDate(bookingEntity.getCheckOutDate())
                .withGuestNumber(bookingEntity.getGuestNumber())
                .withGuest(new GuestResponseDTO(bookingEntity.getGuest()))
                .withProperty(new PropertyResponseDTO(bookingEntity.getProperty()))
                .withStatus(bookingEntity.getStatus());
    }
}
