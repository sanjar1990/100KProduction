package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private ProductEntity toEntity(ProductDTO productDTO) {

        ProductEntity entity = new ProductEntity();
        entity.setName(productDTO.getName());
        entity.setTitle(productDTO.getTitle());
        entity.setAmount(productDTO.getAmount());
        entity.setDescription(productDTO.getDescription());
        entity.setMadeIn(productDTO.getMadeIn());
        entity.setBrand(productDTO.getBrand());
        entity.setCategory(productDTO.getCategory());
        entity.setDiscountPrice(productDTO.getDiscountPrice());
        entity.setMediaList(productDTO.getMediaList());
        entity.setPreviewAttachId(productDTO.getPreviewAttachId());
        entity.setType(productDTO.getType());

        return entity;
    }

    private ProductDTO toDTO(ProductEntity productEntity) {
        ProductDTO dto = new ProductDTO();
        dto.setName(productEntity.getName());
        dto.setTitle(productEntity.getTitle());
        dto.setAmount(productEntity.getAmount());
        dto.setDescription(productEntity.getDescription());
        dto.setMadeIn(productEntity.getMadeIn());
        dto.setBrand(productEntity.getBrand());
        dto.setCategory(productEntity.getCategory());
        dto.setDiscountPrice(productEntity.getDiscountPrice());
        dto.setMediaList(productEntity.getMediaList());
        dto.setPreviewAttachId(productEntity.getPreviewAttachId());
        dto.setType(productEntity.getType());
        return dto;
    }

    public ApiResponseDTO create(ProductDTO productDTO) {
        UUID prtId = SpringSecurityUtil.getCurrentProfileId();//current user

        ProductEntity entity = toEntity(productDTO);
        entity.setPrtId(prtId);
        try {
            ProductEntity saved = productRepository.save(entity);
            return new ApiResponseDTO(true, toDTO(saved));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
