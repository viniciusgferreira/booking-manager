package com.hostfully.bookingmanager.models.builders;

import com.hostfully.bookingmanager.dtos.CreateBlockDTO;
import com.hostfully.bookingmanager.models.Block;

public class BlockBuilder {
    private CreateBlockDTO dto;
    public BlockBuilder fromDTO(CreateBlockDTO dto) {
        this.dto = dto;
        return this;
    }

    public Block build() {
        Block entity = new Block();
        entity.setStartDate(dto.startDate());
        entity.setEndDate(dto.endDate());
        entity.setReason(dto.reason());
        return entity;
    }
}