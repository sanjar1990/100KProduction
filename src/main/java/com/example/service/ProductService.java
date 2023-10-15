package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AttachDTO;
import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttachService attachService;
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
        entity.setTypeInfo(productDTO.getTypeInfo());

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
        dto.setTypeInfo(productEntity.getTypeInfo());
        return dto;
    }

    public ApiResponseDTO create(ProductDTO productDTO, MultipartFile media) {
//        UUID prtId = SpringSecurityUtil.getCurrentProfileId();//current user

        AttachDTO savedAttach = attachService.save(media);
        ProductEntity entity = toEntity(productDTO);
        entity.setPreviewAttachId(savedAttach.getId());
//        entity.setPrtId(prtId);
        try {
            ProductEntity savedProduct = productRepository.save(entity);
            return new ApiResponseDTO(true, toDTO(savedProduct));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
