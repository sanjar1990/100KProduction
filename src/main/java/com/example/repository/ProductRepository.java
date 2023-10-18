package com.example.repository;

import com.example.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("SELECT e FROM ProductEntity e where e.visible=true ORDER BY e.createdDate DESC limit 10")
    Iterable<ProductEntity> findAllByVisibleTrueAndCreatedDateDesc();


    @Query("SELECT e FROM ProductEntity e where e.visible=true ORDER BY e.rating DESC limit 10")
    Iterable<ProductEntity> findAllByRatingDesc();

    Iterable<ProductEntity> findAllByVisibleTrue();

    @Transactional
    @Modifying
    @Query("update ProductEntity p set p.visible=false where p.id=:id")
    void updateVisible(@Param("id") UUID id);
}
