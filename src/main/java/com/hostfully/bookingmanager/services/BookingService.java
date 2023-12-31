package com.hostfully.bookingmanager.services;

import com.hostfully.bookingmanager.dtos.BookingResponseDTO;
import com.hostfully.bookingmanager.dtos.CreateBookingDTO;
import com.hostfully.bookingmanager.dtos.RebookDTO;
import com.hostfully.bookingmanager.dtos.builders.BookingResponseBuilder;
import com.hostfully.bookingmanager.models.Booking;
import com.hostfully.bookingmanager.models.BookingStatus;
import com.hostfully.bookingmanager.models.Guest;
import com.hostfully.bookingmanager.models.Property;
import com.hostfully.bookingmanager.models.builders.BookingBuilder;
import com.hostfully.bookingmanager.repositories.BookingRepository;
import com.hostfully.bookingmanager.repositories.GuestRepository;
import com.hostfully.bookingmanager.repositories.PropertyRepository;
import com.hostfully.bookingmanager.services.exceptions.BookingNotFoundException;
import com.hostfully.bookingmanager.services.exceptions.DateRangeNotAvailable;
import com.hostfully.bookingmanager.services.exceptions.GuestNotFoundException;
import com.hostfully.bookingmanager.services.exceptions.PropertyNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final PropertyRepository propertyRepository;
    private final BookingResponseBuilder bookingResponseBuilder;
    private final DateRangeCheckerService dateRangeCheckerService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          GuestRepository guestRepository,
                          PropertyRepository propertyRepository,
                          BookingResponseBuilder bookingResponseBuilder, DateRangeCheckerService dateRangeCheckerService) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.propertyRepository = propertyRepository;
        this.bookingResponseBuilder = bookingResponseBuilder;
        this.dateRangeCheckerService = dateRangeCheckerService;
    }

    @Transactional
    public BookingResponseDTO createBooking(CreateBookingDTO dto) {
        if (dateRangeCheckerService.isDateRangeNotAvailable(null, dto.checkInDate(), dto.checkOutDate(), dto.propertyId())) throw new DateRangeNotAvailable();
        Guest guestEntity = guestRepository.findById(dto.guestId()).orElseThrow(GuestNotFoundException::new);
        Property propertyEntity = propertyRepository.findById(dto.propertyId()).orElseThrow(PropertyNotFoundException::new);
        Booking bookingEntity = new BookingBuilder().fromDTO(dto).build();
        bookingEntity.setGuest(guestEntity);
        bookingEntity.setProperty(propertyEntity);
        Booking savedEntity = bookingRepository.save(bookingEntity);
        return bookingResponseBuilder.fromEntity(savedEntity).build();
    }

    @Transactional
    public BookingResponseDTO rebook(UUID uid, RebookDTO dto) {
        Booking existingBookingEntity = bookingRepository.findById(uid).orElseThrow(BookingNotFoundException::new);
        if (dateRangeCheckerService.isDateRangeNotAvailable(uid, dto.checkInDate(), dto.checkOutDate(), existingBookingEntity.getProperty().getUid())) throw new DateRangeNotAvailable();
        Booking newBooking = new Booking();
        BeanUtils.copyProperties(existingBookingEntity, newBooking,"uid","checkInDate", "checkOutDate");
        bookingRepository.updateBookingStatus(uid, BookingStatus.REBOOKED);
        newBooking.setCheckInDate(dto.checkInDate());
        newBooking.setCheckOutDate(dto.checkOutDate());
        newBooking.setStatus(BookingStatus.CONFIRMED);
        newBooking = bookingRepository.save(newBooking);
        return bookingResponseBuilder.fromEntity(newBooking).build();
    }

    public BookingResponseDTO getBookingById(UUID uid) {
        Booking entity = bookingRepository.findById(uid).orElseThrow(BookingNotFoundException::new);
        return bookingResponseBuilder.fromEntity(entity).build();
    }

    @Transactional
    public BookingResponseDTO updateBookingById(UUID uid, CreateBookingDTO dto) {
        Booking existingBookingEntity = bookingRepository.findById(uid).orElseThrow(BookingNotFoundException::new);
        bookingRepository.deleteById(uid);
        if (dateRangeCheckerService.isDateRangeNotAvailable(uid, dto.checkInDate(), dto.checkOutDate(), dto.propertyId())) throw new DateRangeNotAvailable();
        BeanUtils.copyProperties(dto, existingBookingEntity);
        BookingStatus bookingStatus = BookingStatus.fromValue(dto.status().toLowerCase());
        existingBookingEntity.setStatus(bookingStatus);
        Booking savedAndUpdatedEntity = bookingRepository.save(existingBookingEntity);
        return bookingResponseBuilder.fromEntity(savedAndUpdatedEntity).build();
    }

    @Transactional
    public void deleteBookingById(UUID uid) {
        if(!bookingRepository.existsById(uid)) throw new BookingNotFoundException();
        bookingRepository.deleteById(uid);
    }

    @Transactional
    public BookingResponseDTO cancelBookingById(UUID uid) {
        Booking existingBookingEntity = bookingRepository.findById(uid).orElseThrow(BookingNotFoundException::new);
        existingBookingEntity.setStatus(BookingStatus.CANCELLED);
        Booking savedAndUpdatedEntity = bookingRepository.save(existingBookingEntity);
        return bookingResponseBuilder.fromEntity(savedAndUpdatedEntity).build();
    }


}
