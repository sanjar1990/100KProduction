package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
    private String id;
    private String profile;
    private String country;
    private String city;
    private String district;
    private String street;

    public AddressDTO() {
    }

    public AddressDTO(String id, String profile, String country, String city, String district, String street) {
        this.id = id;
        this.profile = profile;
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
    }
}
