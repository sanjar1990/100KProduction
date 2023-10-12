package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "attach_id")
    private String attach_id;

    @Column(name = "product_count")
    private Integer count;

    @ManyToOne
    private CategoryEntity category;

}
