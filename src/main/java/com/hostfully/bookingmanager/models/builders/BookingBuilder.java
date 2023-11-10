package com.hostfully.bookingmanager.models.builders;

import com.hostfully.bookingmanager.dtos.CreateBookingDTO;
import com.hostfully.bookingmanager.models.Booking;

public class BookingBuilder {
    private CreateBookingDTO dto;
    public BookingBuilder fromDTO(CreateBookingDTO dto) {
        this.dto = dto;
        return this;
    }

    public Booking build() {
        Booking entity = new Booking();
        entity.setCheckInDate(dto.checkInDate());
        entity.setCheckOutDate(dto.checkOutDate());
        entity.setGuestNumber(dto.guestNumber());
        return entity;
    }
}