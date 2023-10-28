package com.example.dto;

import com.example.entity.ProductEntity;
import com.example.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderProDTO {
    private boolean isStatus;
    private ProductDTO product;
    private ProfileEntity profile;
    private String address;

    public OrderProDTO(boolean isStatus, ProductDTO product, ProfileEntity profile) {
        this.isStatus = isStatus;
        this.product = product;
        this.profile = profile;
    }
}
