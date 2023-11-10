package com.hostfully.bookingmanager.dtos.builders;

import com.hostfully.bookingmanager.dtos.BlockResponseDTO;
import com.hostfully.bookingmanager.dtos.PropertyResponseDTO;
import com.hostfully.bookingmanager.models.Block;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class BlockResponseBuilder {
    private UUID uid;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private PropertyResponseDTO property;

    public BlockResponseBuilder withId(UUID uid) {
        this.uid = uid;
        return this;
    }

    public BlockResponseBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
    public BlockResponseBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }
    public BlockResponseBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public BlockResponseBuilder withProperty(PropertyResponseDTO property) {
        this.property = property;
        return this;
    }

    public BlockResponseDTO build() {
        return  new BlockResponseDTO(uid, startDate, endDate, reason, property);
    }

    public BlockResponseBuilder fromEntity(Block blockEntity) {
        return new BlockResponseBuilder().withId(blockEntity.getUid())
                .withStartDate(blockEntity.getStartDate())
                .withEndDate(blockEntity.getEndDate())
                .withReason(blockEntity.getReason())
                .withProperty(new PropertyResponseDTO(blockEntity.getProperty()));
    }
}
