package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity extends BaseEntity{
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private LocalDateTime birthday;

    @Column
    private String phone;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileRole role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private AttachEntity photoId;

    @OneToOne(mappedBy = "profile")
    private AddressEntity address;
}
