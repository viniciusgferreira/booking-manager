package com.hostfully.bookingmanager.dtos;

import com.hostfully.bookingmanager.models.Property;

import java.util.UUID;

public record PropertyResponseDTO(
         UUID uid,
         String type,
         String name,
         String address,
         String city,
         String state,
         String pictureURL
) {
    public PropertyResponseDTO(Property entity){
        this(entity.getUid(),
                entity.getType(),
                entity.getName(),
                entity.getAddress(),
                entity.getCity(),
                entity.getState(),
                entity.getPictureURL());
    }
}
