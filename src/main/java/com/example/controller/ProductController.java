package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String all(Model model) {
        return "productCRUD";
    }
    @GetMapping("/toProduct")
    public String toProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("isEdit", false);
        return "product-add";
    }
    @PostMapping("/create")
//    @PreAuthorize("hasRole('SELLER')")
    public String create(@ModelAttribute ProductDTO productDTO, Model model,
                             @RequestParam("media") MultipartFile media) {
        ApiResponseDTO response = productService.create(productDTO, media);
        if (response.isStatus()) { // 200 true
            return "redirect:/api/v1/product/all";
        }
        return "product-add";
    }

}
