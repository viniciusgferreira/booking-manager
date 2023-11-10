package com.hostfully.bookingmanager.controllers;

import com.hostfully.bookingmanager.dtos.BlockResponseDTO;
import com.hostfully.bookingmanager.dtos.CreateBlockDTO;
import com.hostfully.bookingmanager.services.BlockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/blocks")
public class BlockController {
    @Autowired
    BlockService service;

    @PostMapping
    public ResponseEntity<BlockResponseDTO> createBlock(@Valid @RequestBody CreateBlockDTO dto){
        BlockResponseDTO blockResponseDTO = service.createBlock(dto);
        URI location = URI.create("/api/blocks/" + blockResponseDTO.uid());
        return ResponseEntity.created(location).body(blockResponseDTO);
    }

    @GetMapping(value = "/{uid}")
    public ResponseEntity<BlockResponseDTO> getBlockById(@PathVariable UUID uid) {
        BlockResponseDTO blockResponseDTO = service.getBlockById(uid);
        return ResponseEntity.ok(blockResponseDTO);
    }

    @PutMapping(value = "/{uid}")
    public ResponseEntity<BlockResponseDTO> updateBlockById(@PathVariable UUID uid, @Valid @RequestBody CreateBlockDTO dto) {
        BlockResponseDTO blockResponseDTO = service.updateBlockById(uid, dto);
        return ResponseEntity.ok(blockResponseDTO);
    }

    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Void> deleteBlockById(@PathVariable UUID uid) {
        service.deleteBlockById(uid);
        return ResponseEntity.noContent().build();
    }

}
