package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String all(Model model) {
        return "productCRUD";
    }
    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        ApiResponseDTO response = productService.create(productDTO);
        return response.isStatus() ? (ProductDTO) response.getData() : null;
    }

    @PutMapping
    @PreAuthorize("hasRole('SELLER')")
    public ProductDTO update(@RequestBody ProductDTO productDTO) {
        ApiResponseDTO response = productService.create(productDTO);
        return response.isStatus() ? (ProductDTO) response.getData() : null;
    }
}
