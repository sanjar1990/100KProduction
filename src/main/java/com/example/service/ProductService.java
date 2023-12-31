package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AttachDTO;
import com.example.dto.OrderProDTO;
import com.example.dto.ProductDTO;
import com.example.entity.ProductEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProductRepository;
import com.example.repository.ProfileRepository;
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
    @Autowired
    private ProfileRepository profileRepository;
    private ProductEntity toEntity(ProductDTO productDTO) {

        ProductEntity entity = new ProductEntity();
        entity.setId(productDTO.getId());
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
        dto.setId(productEntity.getId());
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
//        UUID prtId = SpringSecurityUtil.getCurrentProfileId();//current user //TODO

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
        Iterable<ProductEntity> all = productRepository.findAllByVisibleTrue();
        LinkedList<ProductDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public OrderProDTO order(String id) {
        Optional<ProductEntity> optional = productRepository
                .findById(UUID.fromString(id));
//        ProfileEntity profile = SpringSecurityUtil.getProfileEntity();
        ProfileEntity profileEntity = profileRepository.getById(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"));
        if (optional.isPresent()) {
            return new OrderProDTO(true, toDTO(optional.get()), profileEntity);
        }
        throw new ItemNotFoundException("ITEM NOT FOUND!!!");
    }

    public List<ProductDTO> getNewProducts() {
        Iterable<ProductEntity> entities = productRepository.findAllByVisibleTrueAndCreatedDateDesc();
        LinkedList<ProductDTO> dtoList = new LinkedList<>();
        entities.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public List<ProductDTO> getTopProducts() {
        Iterable<ProductEntity> entities = productRepository.findAllByRatingDesc();
        LinkedList<ProductDTO> dtoList = new LinkedList<>();
        entities.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public ApiResponseDTO delete(String id) {
        boolean isExistsProduct = productRepository.existsById(UUID.fromString(id));
        if (isExistsProduct) {
            productRepository.updateVisible(UUID.fromString(id));
            return new ApiResponseDTO(true, "SUCCESS DELETED !!!");
        }
        throw new ItemNotFoundException("ITEM NOT FOUND !!!");
    }

    public ApiResponseDTO update(ProductDTO dto, String id, MultipartFile media) {
        ProductEntity entity = toEntity(dto);

        entity.setId(UUID.fromString(id));
        if (media.isEmpty()) {
            String previewAttachId = productRepository.getById(UUID.fromString(id)).getPreviewAttachId();
            entity.setPreviewAttachId(previewAttachId);
        } else {
            AttachDTO savedAttach = attachService.save(media);
            entity.setPreviewAttachId(savedAttach.getId());
        }

        productRepository.save(entity);
        return new ApiResponseDTO(true, "saved");
    }
    public ProductDTO getById(String id){
        Optional<ProductEntity> optional=productRepository.findById(UUID.fromString(id));
        if(optional.isEmpty()){
            throw new ItemNotFoundException("Product not found");
        }else {
            return toDTO(optional.get());
        }
    }

    public List<ProductDTO> getAllByCategoryId(String id) {
        Iterable<ProductEntity> productEntities = productRepository.getAllByCategoryId(UUID.fromString(id));
        List<ProductDTO> productDTOList = new LinkedList<>();
        productEntities.forEach(product -> {
            productDTOList.add(toDTO(product));
        });
        return productDTOList;
    }

    public List<ProductDTO> getAllByPrtId(String prtId) {
        Iterable<ProductEntity> productEntities = productRepository.getAllByPrtIdAndVisibleTrue(UUID.fromString(prtId));
        List<ProductDTO> productDTOList = new LinkedList<>();
        productEntities.forEach(product -> {
            productDTOList.add(toDTO(product));
        });
        return productDTOList;
    }
}
