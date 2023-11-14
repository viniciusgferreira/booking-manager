package com.hostfully.bookingmanager.services;

import com.hostfully.bookingmanager.dtos.BookingResponseDTO;
import com.hostfully.bookingmanager.dtos.CreateBookingDTO;
import com.hostfully.bookingmanager.dtos.GuestResponseDTO;
import com.hostfully.bookingmanager.dtos.PropertyResponseDTO;
import com.hostfully.bookingmanager.dtos.builders.BookingResponseBuilder;
import com.hostfully.bookingmanager.models.*;
import com.hostfully.bookingmanager.models.builders.BookingBuilder;
import com.hostfully.bookingmanager.repositories.BookingRepository;
import com.hostfully.bookingmanager.repositories.GuestRepository;
import com.hostfully.bookingmanager.repositories.PropertyRepository;
import com.hostfully.bookingmanager.services.exceptions.DateRangeNotAvailable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @InjectMocks
    BookingService underTest;

    @Mock
    BookingRepository bookingRepository;
    @Mock
    GuestRepository guestRepository;
    @Mock
    PropertyRepository propertyRepository;
    @Mock
    BookingResponseBuilder bookingResponseBuilder;
    @Mock
    DateRangeCheckerService dateRangeCheckerService;

    private Guest guest;
    private Property property;

    @BeforeEach
    public void setUp() {
        guest = new Guest(UUID.randomUUID(), "John", "Cena", LocalDate.parse("1990-01-01"), "photo.jpg", "123 st", "New York", "NY", "5555-5555", "john@email.com", LocalDateTime.now());
        property = new Property(UUID.randomUUID(), "Apartment", "Luxury apt", true, 2, 10, 4, 2, "123 street", "Orlando", "FL", "pic.jpg");
    }

    @Test
    public void shouldCreateAValidBooking(){
        LocalDate checkInDate = LocalDate.parse("2023-10-12");
        LocalDate checkOutDate = LocalDate.parse("2023-10-15");
        Integer guestNumber = 2;
        CreateBookingDTO dto = new CreateBookingDTO(checkInDate, checkOutDate, guestNumber, guest.getUid(), property.getUid(), BookingStatus.CONFIRMED.getValue().toLowerCase());
        Booking entity = new BookingBuilder().fromDTO(dto).build();
        BookingResponseDTO expectedResponse = new BookingResponseDTO(entity.getUid(), checkInDate, checkOutDate, guestNumber, new GuestResponseDTO(guest), new PropertyResponseDTO(property), BookingStatus.CONFIRMED);

        when(guestRepository.findById(guest.getUid())).thenReturn(Optional.of(guest));
        when(propertyRepository.findById(property.getUid())).thenReturn(Optional.of(property));
        when(bookingRepository.save(any(Booking.class))).thenReturn(entity);
        when(bookingResponseBuilder.fromEntity(Mockito.any(Booking.class))).thenReturn(bookingResponseBuilder);
        when(bookingResponseBuilder.build()).thenReturn(expectedResponse);
        when(dateRangeCheckerService.isDateRangeNotAvailable(any(), any(LocalDate.class), any(LocalDate.class), any(UUID.class))).thenReturn(false);

        BookingResponseDTO result = underTest.createBooking(dto);

        assertEquals(expectedResponse, result);
        assertEquals(result.uid(), entity.getUid());
        assertEquals(result.checkInDate(), checkInDate);
        assertEquals(result.checkOutDate(), checkOutDate);
        assertEquals(result.guestNumber(), guestNumber);
        assertEquals(result.property().uid(), property.getUid());
    }

    @Test
    public void BlockedDateShouldReturnError(){
        LocalDate checkInDate = LocalDate.parse("2023-10-12");
        LocalDate checkOutDate = LocalDate.parse("2023-10-15");
        Integer guestNumber = 2;
        CreateBookingDTO dto = new CreateBookingDTO(checkInDate, checkOutDate, guestNumber, guest.getUid(), property.getUid(), BookingStatus.CONFIRMED.getValue().toLowerCase());
        Block blockEntity = new Block();
        blockEntity.setStartDate(LocalDate.parse("2023-10-10"));
        blockEntity.setEndDate(LocalDate.parse("2023-10-14"));
        when(dateRangeCheckerService.isDateRangeNotAvailable(any(), any(LocalDate.class), any(LocalDate.class), any(UUID.class))).thenReturn(true);

        assertThrowsExactly(DateRangeNotAvailable.class, () -> underTest.createBooking(dto));

    }

    @Test
    public void shouldUpdateAValidBooking(){
        LocalDate checkInDate = LocalDate.parse("2023-10-12");
        LocalDate checkOutDate = LocalDate.parse("2023-10-15");
        Integer guestNumber = 2;
        CreateBookingDTO dto = new CreateBookingDTO(checkInDate, checkOutDate, guestNumber, guest.getUid(), property.getUid(), BookingStatus.CONFIRMED.getValue());
        Booking entity = new BookingBuilder().fromDTO(dto).build();
        entity.setProperty(this.property);
        entity.setGuest(this.guest);
        BookingResponseDTO expectedResponse = new BookingResponseDTO(entity.getUid(), checkInDate, checkOutDate, guestNumber, new GuestResponseDTO(guest), new PropertyResponseDTO(property), BookingStatus.CONFIRMED);

        when(bookingRepository.findById(any(UUID.class))).thenReturn(Optional.of(entity));
        doNothing().when(bookingRepository).deleteById(any(UUID.class));
        when(dateRangeCheckerService.isDateRangeNotAvailable(any(), any(LocalDate.class), any(LocalDate.class), any(UUID.class))).thenReturn(false);
        when(bookingRepository.save(any(Booking.class))).thenReturn(entity);
        when(bookingResponseBuilder.fromEntity(any(Booking.class))).thenReturn(bookingResponseBuilder);
        when(bookingResponseBuilder.build()).thenReturn(expectedResponse);


        BookingResponseDTO result = underTest.updateBookingById(entity.getUid(), dto);

        verify(bookingRepository).findById(entity.getUid());
        assertEquals(result, expectedResponse);
        assertEquals(result.uid(), entity.getUid());
        assertEquals(result.checkInDate(), checkInDate);
        assertEquals(result.checkOutDate(), checkOutDate);
        assertEquals(result.guestNumber(), guestNumber);
        assertEquals(result.property().uid(), property.getUid());
    }

    @Test
    public void shouldDeleteAValidBooking(){
        Booking entity = new Booking();

        when(bookingRepository.existsById(entity.getUid())).thenReturn(true);

        underTest.deleteBookingById(entity.getUid());

        verify(bookingRepository).deleteById(entity.getUid());

    }
}
