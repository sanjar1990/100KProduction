package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "photo_id")
    private UUID photoId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;

//    @Column(name = "product_count") //TODO
//    private Integer productCount;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_count", insertable = false, updatable = false)
//    private ProductEntity productEntity;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    @Column(name = "prt_id")
    private UUID prtId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prt_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;


}

