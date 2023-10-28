package com.example.repository;

import com.example.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update ProfileEntity set password = :currentCode where email = :username")
    void updatePassword(@Param("username") String username, @Param("currentCode") String currentCode);

    ProfileEntity getById(UUID id);
}
