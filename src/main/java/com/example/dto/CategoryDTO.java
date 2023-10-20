package com.example.dto;

import com.example.entity.AttachEntity;
import com.example.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
    @NotNull(message = "COUNT SHOULD NOT BE NULL !!!")
    private Integer productCount;
    private ProductEntity productEntity;
}
