package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ProfileEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class ProductDTO extends BaseDTO {
    @NotNull
    private String name;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Double price;
    private Double discountPrice;
    private UUID previewAttachId;
    private AttachEntity attachEntity;
    @NotNull
    private Integer amount;
    @NotNull
    private String typeInfo; // String -> color, size, etc, it's mixed
    @NotNull
    private String brand; //guchi
    @NotNull
    private String madeIn;//China
    private CategoryEntity category;
    private List<AttachEntity> mediaList;
    private UUID prtId;
    private ProfileEntity profileEntity;
    private int rating = 0;
    private int viewCount = 0;
}