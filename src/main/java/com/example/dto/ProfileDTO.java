package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ProfileDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime birthday;
    private String phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private boolean visible;
    private LocalDateTime createdDate;
    private String photoId;
    private String addressId;
    private String jwt;


    public ProfileDTO() {
    }

    public ProfileDTO(UUID id, String name, String surname, String email, LocalDateTime birthday, String phone, String password, ProfileStatus status, ProfileRole role, boolean visible, LocalDateTime createdDate, String photoId, String addressId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.role = role;
        this.visible = visible;
        this.createdDate = createdDate;
        this.photoId = photoId;
        this.addressId = addressId;
    }
}
