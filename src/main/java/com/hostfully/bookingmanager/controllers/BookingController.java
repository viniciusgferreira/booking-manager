package com.hostfully.bookingmanager.controllers;

import com.hostfully.bookingmanager.dtos.BookingResponseDTO;
import com.hostfully.bookingmanager.dtos.CreateBookingDTO;
import com.hostfully.bookingmanager.dtos.RebookDTO;
import com.hostfully.bookingmanager.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {
    @Autowired
    BookingService service;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody CreateBookingDTO dto){
        BookingResponseDTO bookingResponseDTO = service.createBooking(dto);
        URI location = URI.create("/api/bookings/" + bookingResponseDTO.uid());
        return ResponseEntity.created(location).body(bookingResponseDTO);
    }

    @GetMapping(value = "/{uid}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable UUID uid) {
        BookingResponseDTO bookingResponseDTO = service.getBookingById(uid);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @PutMapping(value = "/{uid}")
    public ResponseEntity<BookingResponseDTO> updateBookingById(@PathVariable UUID uid, @Valid @RequestBody CreateBookingDTO dto) {
        BookingResponseDTO bookingResponseDTO = service.updateBookingById(uid, dto);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @PatchMapping(value = "/{uid}")
    public ResponseEntity<BookingResponseDTO> cancelBookingById(@PathVariable UUID uid) {
        BookingResponseDTO bookingResponseDTO = service.cancelBookingById(uid);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable UUID uid) {
        service.deleteBookingById(uid);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{uid}")
    public ResponseEntity<BookingResponseDTO> rebook(@PathVariable UUID uid, @Valid @RequestBody RebookDTO dto){
        BookingResponseDTO bookingResponseDTO = service.rebook(uid, dto);
        URI location = URI.create("/api/bookings/" + bookingResponseDTO.uid());
        return ResponseEntity.created(location).body(bookingResponseDTO);
    }

}
