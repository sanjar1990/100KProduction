package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
        public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
            CategoryEntity entity = new CategoryEntity();
            entity.setName(categoryDTO.getTitle());
            entity.setInfo(categoryDTO.getInfo());
            entity.setPhotoId(categoryDTO.getPhotoId());
//            entity.setProductCount(categoryDTO.getProductCount());
            return entity;
        }

        public static CategoryDTO toDTO(CategoryEntity categoryEntity) {
            CategoryDTO dto = new CategoryDTO();
            dto.setTitle(categoryEntity.getName());
            dto.setInfo(categoryEntity.getInfo());
            dto.setPhotoId(categoryEntity.getPhotoId());
//            dto.setProductCount(categoryEntity.getProductCount());
            return dto;
        }

    public ApiResponseDTO create(CategoryDTO categoryDTO) {
        CategoryEntity entity = toEntity(categoryDTO);
        CategoryEntity saved = categoryRepository.save(entity);
        return new ApiResponseDTO(true, toDTO(saved));
    }

}
