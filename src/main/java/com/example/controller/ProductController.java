package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ProductDTO;
import com.example.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/gotoCreate")
    public String toProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("isEdit", false);
        return "product/product-add";
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('SELLER')")
    public String create(@ModelAttribute ProductDTO productDTO,
                         @RequestParam("productImage") MultipartFile media,
                         Model model) {


        ApiResponseDTO response = productService.create(productDTO, media);
        if (response.isStatus()) { // 200 true
            return "redirect:/api/v1/product/all";
        }
        return "product/product-add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/api/v1/product/all";
    }

    @GetMapping("/gotoUpdate/{id}")
    public String goToUpdate(@PathVariable String id,
                         Model model) {

//        ApiResponseDTO response = productService.update(id);
        model.addAttribute("productDTO",productService.getById(id));
        model.addAttribute("isEdit",true);
        return "product/product-add";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute ProductDTO productDTO,
                         @PathVariable String id,
                       @RequestParam("productImage")  MultipartFile media){
        productService.update(productDTO,id, media);
        return "redirect:/api/v1/product/all";
    }

    @GetMapping("/all")// TODO only for seller
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

    @GetMapping("/doc")
    public String productAll() {
        return "product/product-doc";
    }

}
