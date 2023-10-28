package com.example.dto;

import com.example.entity.ProductEntity;
import com.example.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.UUID;
@Setter
@Getter
public class OrderDTO {
    private String productId;
    private ProductEntity productEntity;
    private String prtId;
    private ProfileEntity profileEntity;
    private String name;
    private String phone;
    private String address;
}
