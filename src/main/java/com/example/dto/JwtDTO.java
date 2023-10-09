package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class JwtDTO {
    private UUID id;
    private ProfileRole role;
    private String phone;

    public JwtDTO(UUID id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JwtDTO(String phone, ProfileRole role) {
        this.role = role;
        this.phone = phone;
    }
}
