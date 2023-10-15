package com.example.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public class StringBaseDTO {
    private String id;
    private Boolean visible;
    private LocalDateTime createdDate;
}

