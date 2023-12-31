package com.example.repository;

import com.example.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, UUID> {
    List<EmailHistoryEntity> findByEmail(String email);
    List<EmailHistoryEntity> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
    Page<EmailHistoryEntity> findAllBy(Pageable pageable);
}
