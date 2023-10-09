package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByEmail(String email);
}
