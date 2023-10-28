package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.OrderDTO;
import com.example.dto.OrderProDTO;
import com.example.dto.ProductDTO;
import com.example.entity.ProfileEntity;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/gotoCreate")
    public String toProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categoryList", categoryService.getAllVisibleTrue());
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

        model.addAttribute("productDTO", productService.getById(id));
        model.addAttribute("categoryList", categoryService.getAllVisibleTrue());
        model.addAttribute("isEdit", true);
        return "product/product-add";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute ProductDTO productDTO,
                         @PathVariable String id,
                         @RequestParam("productImage") MultipartFile media) {
        productService.update(productDTO, id, media);
        return "redirect:/api/v1/product/all";
    }

    @GetMapping("/all")// TODO only for seller
    public String all(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "product/product-all";
    }

    @GetMapping("/all-products")// for customer
    public String getAll(Model model) {
        model.addAttribute("productList", productService.getAll());

        return "product/product-all";
    }

    @GetMapping("/get-all-products")// for customer
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("categoryList", categoryService.getAllVisibleTrue());

        return "category/categories";
    }

    @GetMapping("/doc")
    public String productAll() {
        return "product/product-doc";
    }


    @GetMapping("/get-all-by-category-id/{id}") // for everyone
    public String getAllByCategoryId(Model model,
                                     @PathVariable("id") String id) {
        model.addAttribute("productList", productService.getAllByCategoryId(id));
        model.addAttribute("categoryList", categoryService.getAllVisibleTrue());

        return "category/categories";
    }

    @GetMapping("/get-all-by-prtId/{prtId}") // for everyone
    public String getAllByPrtId(Model model,
                                @PathVariable("prtId") String prtId) {
        model.addAttribute("productList", productService.getAllByPrtId(prtId));

        return "category/categories";
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") String id,
                        Model model) {
        System.out.println(id); // TODO CHECK prtID
         OrderProDTO response = productService.order(id);
//        ProfileEntity profile = SpringSecurityUtil.getProfileEntity();
        if (response.isStatus()) {
            model.addAttribute("order_pro", response);
            model.addAttribute("product", response.getProduct());
            model.addAttribute("profile", response.getProfile());
            model.addAttribute("order", new OrderDTO());
            return "product/product-order";
        }
        return "product/not-found";
    }




}
