package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public class StringBaseEntity {
    @Id
    private String id;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
