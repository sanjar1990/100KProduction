package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AttachDTO;
import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttachService attachService;

    public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryDTO.getName());
        entity.setInfo(categoryDTO.getInfo());
        entity.setPhotoId(categoryDTO.getPhotoId());
//            entity.setCategoryCount(categoryDTO.getCategoryCount()); //TODO soon
        return entity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        dto.setInfo(categoryEntity.getInfo());
        dto.setPhotoId(categoryEntity.getPhotoId());
//            dto.setCategoryCount(categoryEntity.getCategoryCount()); //TODO
        dto.setUrlImg(attachService.getUrl(dto.getPhotoId()));

        return dto;
    }


    public ApiResponseDTO create(CategoryDTO categoryDTO, MultipartFile media) {
        //        UUID prtId = SpringSecurityUtil.getCurrentProfileId();//current user //TODO
        AttachDTO savedAttach = attachService.save(media);

        CategoryEntity entity = toEntity(categoryDTO);
        entity.setPhotoId(savedAttach.getId());
//        entity.setPrtId(prtId);
        try {
            CategoryEntity savedCategory = categoryRepository.save(entity);
            return new ApiResponseDTO(true, toDTO(savedCategory));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<CategoryDTO> getAll() {
            Iterable<CategoryEntity> all = categoryRepository.findAllByVisibleTrue();
        LinkedList<CategoryDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public ApiResponseDTO delete(String id) {
        boolean isExistsCategory = categoryRepository.existsById(UUID.fromString(id));
        if (isExistsCategory) {
            categoryRepository.updateVisible(UUID.fromString(id));
            return new ApiResponseDTO(true, "SUCCESS DELETED !!!");
        }
        throw new ItemNotFoundException("ITEM NOT FOUND !!!");
    }

    public ApiResponseDTO update(CategoryDTO dto, String id, MultipartFile media) {
        CategoryEntity entity = toEntity(dto);

        entity.setId(UUID.fromString(id));
        if (media.isEmpty()) {
            String previewAttachId = categoryRepository.getById(UUID.fromString(id)).getPhotoId();
            entity.setPhotoId(previewAttachId);
        } else {
            AttachDTO savedAttach = attachService.save(media);
            entity.setPhotoId(savedAttach.getId());
        }

        categoryRepository.save(entity);
        return new ApiResponseDTO(true, "saved");
    }

    public CategoryDTO getById(String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(UUID.fromString(id));
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Category not found");
        } else {
            return toDTO(optional.get());
        }
    }
}
