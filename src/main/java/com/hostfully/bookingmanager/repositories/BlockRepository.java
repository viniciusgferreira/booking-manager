package com.hostfully.bookingmanager.repositories;

import com.hostfully.bookingmanager.models.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlockRepository extends JpaRepository<Block, UUID> {

    List<Block> findByPropertyUidAndEndDateAfterAndStartDateBefore(
            UUID propertyUid, LocalDate startDate, LocalDate endDate);
}
