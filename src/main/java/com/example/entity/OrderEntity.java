package com.example.entity;

import com.example.entity.BaseEntity;
import com.example.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column(name = "product_id")
    private UUID productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    @Column(name = "prt_id")
    private UUID prtId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prt_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;


}
