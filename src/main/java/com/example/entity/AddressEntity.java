package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @Column(name = "profile_id")
    private ProfileEntity profile;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String street;
}
