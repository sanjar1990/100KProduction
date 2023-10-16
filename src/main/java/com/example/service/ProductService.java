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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
        entity.setPrice(productDTO.getPrice());
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
        dto.setPrice(productEntity.getPrice());
        dto.setDescription(productEntity.getDescription());
        dto.setMadeIn(productEntity.getMadeIn());
        dto.setBrand(productEntity.getBrand());
        dto.setCategory(productEntity.getCategory());
        dto.setDiscountPrice(productEntity.getDiscountPrice());
        dto.setMediaList(productEntity.getMediaList());
        dto.setPreviewAttachId(productEntity.getPreviewAttachId());
        dto.setTypeInfo(productEntity.getTypeInfo());
        dto.setUrl(attachService.getUrl(productEntity.getPreviewAttachId()));
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

    public List<ProductDTO> getAll() {
        Iterable<ProductEntity> all = productRepository.findAll();
        LinkedList<ProductDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public ApiResponseDTO order(String id) {
        Optional<ProductEntity> optional = productRepository
                .findById(UUID.fromString(id));
        if (optional.isPresent()) {
            return new ApiResponseDTO(true, toDTO(optional.get()));
        }
        return new ApiResponseDTO(false, "ITEM NOT FOUND !!!");
    }
}
