package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/toProduct")
    public String toProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("isEdit", false);
        return "product/product-add";
    }
    @PostMapping("/create")
//    @PreAuthorize("hasRole('SELLER')")
    public String create(@ModelAttribute ProductDTO productDTO, Model model,
                             @RequestParam("media") MultipartFile media) {
        ApiResponseDTO response = productService.create(productDTO, media);
        if (response.isStatus()) { // 200 true
            return "redirect:/api/v1/product/all";
        }
        return "product/product-add";
    }

    @GetMapping("/all")
    public String all(Model model) {
        List<ProductDTO> all = productService.getAll();
        model.addAttribute("productList", all);
        return "product/product-all";
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") String id,
                        Model model) {
        System.out.println(id);
        ApiResponseDTO response = productService.order(id);
        if (response.isStatus()) {
            model.addAttribute("product", response.getData());
            return "product/product-order";
        }
        return "product/not-found";
    }

}
