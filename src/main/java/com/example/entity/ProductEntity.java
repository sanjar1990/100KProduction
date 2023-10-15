package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "preview_attach_id")
    private UUID previewAttachId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preview_attach_id", updatable = false, insertable = false)
    private AttachEntity attachEntity;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "type_info")
    private String typeInfo; // color, size, etc, it's mixed

    @Column(name = "brand")
    private String brand; //guchi

    @Column(name = "made_in")
    private String madeIn;//China

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_list") // videos with photos for carousel
    private List<AttachEntity> mediaList;

    @Column(name = "prt_id")
    private UUID prtId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prt_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;

    @Column(name = "rating")
    private int rating = 0;

    @Column(name = "view_count")
    private int viewCount = 0;

}

//    @Column(name = "like_count")//TODO
//    private int like_count = 0;
//
//    @Column(name = "dislike_count")
//    private int dislike_count = 0;