package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.ProductEntity;
import com.example.entity.ProfileEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CategoryDTO extends BaseDTO {
    @NotNull(message = "NAME SHOULD NOT BE NULL !!!")
    private String name;
    @NotNull(message = "INFO SHOULD NOT BE NULL !!!")
    private String info;
    @NotNull(message = "IMAGE SHOULD NOT BE NULL !!!")
    private String photoId;
    private AttachEntity photo;
    private Integer productCount;
    private ProductEntity productEntity;
    private String urlImg;
//    @Column(name = "product_count") //TODO
//    private Integer productCount;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_count", insertable = false, updatable = false)
//    private ProductEntity productEntity;

    private List<ProductEntity> products;
    private UUID prtId;
    private ProfileEntity profileEntity;
}
