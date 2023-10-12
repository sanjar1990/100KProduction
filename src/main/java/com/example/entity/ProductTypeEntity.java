package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product_type")
public class ProductTypeEntity extends BaseEntity{
    @Column(name = "type")
    private String type; // color, size, etx
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}