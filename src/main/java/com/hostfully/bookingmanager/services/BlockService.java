package com.hostfully.bookingmanager.services;

import com.hostfully.bookingmanager.dtos.BlockResponseDTO;
import com.hostfully.bookingmanager.dtos.CreateBlockDTO;
import com.hostfully.bookingmanager.dtos.builders.BlockResponseBuilder;
import com.hostfully.bookingmanager.models.Block;
import com.hostfully.bookingmanager.models.Property;
import com.hostfully.bookingmanager.models.builders.BlockBuilder;
import com.hostfully.bookingmanager.repositories.BlockRepository;
import com.hostfully.bookingmanager.repositories.PropertyRepository;
import com.hostfully.bookingmanager.services.exceptions.BlockNotFoundException;
import com.hostfully.bookingmanager.services.exceptions.DateRangeNotAvailable;
import com.hostfully.bookingmanager.services.exceptions.PropertyNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    private final PropertyRepository propertyRepository;
    private final BlockResponseBuilder blockResponseBuilder;
    private final DateRangeCheckerService dateRangeCheckerService;


    @Autowired
    public BlockService(BlockRepository blockRepository,
                        PropertyRepository propertyRepository,
                        BlockResponseBuilder blockResponseBuilder, DateRangeCheckerService dateRangeCheckerService) {
        this.blockRepository = blockRepository;
        this.propertyRepository = propertyRepository;
        this.blockResponseBuilder = blockResponseBuilder;
        this.dateRangeCheckerService = dateRangeCheckerService;
    }

    @Transactional
    public BlockResponseDTO createBlock(CreateBlockDTO dto) {
        if (dateRangeCheckerService.isBooked(null,dto.startDate(), dto.endDate(), dto.propertyId())) throw new DateRangeNotAvailable();
        Property propertyEntity = propertyRepository.findById(dto.propertyId()).orElseThrow(PropertyNotFoundException::new);
        Block blockEntity = new BlockBuilder().fromDTO(dto).build();
        blockEntity.setProperty(propertyEntity);
        Block savedEntity = blockRepository.save(blockEntity);
        return blockResponseBuilder.fromEntity(savedEntity).build();
    }

    public BlockResponseDTO getBlockById(UUID uid) {
        Block entity = blockRepository.findById(uid).orElseThrow(BlockNotFoundException::new);
        return blockResponseBuilder.fromEntity(entity).build();
    }

    @Transactional
    public BlockResponseDTO updateBlockById(UUID uid, CreateBlockDTO dto) {
        Block existingBlockEntity = blockRepository.findById(uid).orElseThrow(BlockNotFoundException::new);
        if (dateRangeCheckerService.isBooked(uid, dto.startDate(), dto.endDate(), dto.propertyId())) throw new DateRangeNotAvailable();
        BeanUtils.copyProperties(dto, existingBlockEntity);
        Block savedAndUpdatedEntity = blockRepository.save(existingBlockEntity);
        return blockResponseBuilder.fromEntity(savedAndUpdatedEntity).build();
    }

    @Transactional
    public void deleteBlockById(UUID uid) {
        if(!blockRepository.existsById(uid)) throw new BlockNotFoundException();
        blockRepository.deleteById(uid);
    }
}
