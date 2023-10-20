package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.CategoryDTO;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/gotoCreate")
    public String toProduct(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        model.addAttribute("isEdit", false);
        return "category/category-add";
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public String create(@ModelAttribute CategoryDTO categoryDTO,
                         @RequestParam("media") MultipartFile media,
                         Model model) {
        ApiResponseDTO response = categoryService.create(categoryDTO, media);
        if (response.isStatus()) { // 200 true
            return "redirect:/api/v1/category/all";
        }
        return "category/category-add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        categoryService.delete(id);
        return "redirect:/api/v1/category/all";
    }

    @GetMapping("/gotoUpdate/{id}")
    public String goToUpdate(@PathVariable String id,
                             Model model) {

        model.addAttribute("categoryDTO", categoryService.getById(id));
        model.addAttribute("isEdit", true);
        return "category/category-add";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute CategoryDTO categoryDTO,
                         @PathVariable String id,
                         MultipartFile media) {
        categoryService.update(categoryDTO, id, media);
        return "redirect:/api/v1/category/all";
    }

    @GetMapping("/all")// TODO only for admin
    public String all(Model model) {
        List<CategoryDTO> all = categoryService.getAll();
        model.addAttribute("categoryList", all);
        return "category/category-all";
    }
}
