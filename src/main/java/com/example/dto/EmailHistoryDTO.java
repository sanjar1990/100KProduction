package com.example.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class EmailHistoryDTO {
    private UUID id;
    private String email;
    private String message;
    private String status;
    private LocalDateTime createdDate;

    public EmailHistoryDTO() {
    }

    public EmailHistoryDTO(UUID id, String email, String message, String status, LocalDateTime createdDate) {
        this.id = id;
        this.email = email;
        this.message = message;
        this.status = status;
        this.createdDate = createdDate;
    }
}
