package com.example.controller;

import com.example.dto.ProductDTO;
import com.example.entity.ProfileEntity;
import com.example.service.ProductService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.extras.springsecurity6.util.SpringSecurityContextUtils;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("auth_success", true);

        List<ProductDTO> newProducts = productService.getNewProducts(); //
        List<ProductDTO> topProducts = productService.getTopProducts(); //
        model.addAttribute("newProducts", newProducts);
        model.addAttribute("topProducts", topProducts);
        return "index";
    }

//    @RequestMapping(value = "/home/auth-success", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/home/auth-success")
    public String afterSuccessfullyLogin(Model model, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("message", "Account created!");
        return "redirect:/home";
    }

    @RequestMapping(value = "/home/auth-failed", method = {RequestMethod.GET, RequestMethod.POST})
    public String afterLoginFailed() {
        return "index";
    }
}
