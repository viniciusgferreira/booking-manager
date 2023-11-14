package com.hostfully.bookingmanager.controllers.validations;

import com.hostfully.bookingmanager.dtos.CreateBlockDTO;
import com.hostfully.bookingmanager.dtos.CreateBookingDTO;
import com.hostfully.bookingmanager.dtos.RebookDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateTimeRangeValidator implements ConstraintValidator<DateTimeRange, Object> {

    private LocalDate start;
    private LocalDate end;
    private String message;
    private String node;

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }

        if (dto instanceof CreateBookingDTO) {
            this.start = ((CreateBookingDTO) dto).checkInDate();
            this.end = ((CreateBookingDTO) dto).checkOutDate();
            this.message = "CheckOut date must be greater than CheckIn date.";
            this.node = "checkOutDate";
        }

        if (dto instanceof CreateBlockDTO) {
            this.start = ((CreateBlockDTO) dto).startDate();
            this.end = ((CreateBlockDTO) dto).endDate();
            this.message = "End date must be greater than start date.";
            this.node = "endDate";
        }

        if (dto instanceof RebookDTO) {
            this.start = ((RebookDTO) dto).checkInDate();
            this.end = ((RebookDTO) dto).checkOutDate();
            this.message = "CheckOut date must be greater than CheckIn date.";
            this.node = "checkOutDate";
        }

        boolean isValid = this.start.isBefore(this.end);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(node)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}