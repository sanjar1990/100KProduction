package com.example.repository;

import com.example.entity.CategoryEntity;
import com.example.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Iterable<CategoryEntity> findAllByVisibleTrue();
    @Transactional
    @Modifying
    @Query("update CategoryEntity p set p.visible=false where p.id=:id")
    void updateVisible(@Param("id") UUID id);
}
