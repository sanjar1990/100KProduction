package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {
    @GetMapping("/admin")
    public String adminDashboard(Model model){
        model.addAttribute("page", "adminFullPage");
        return "dashboard";
    }

    @GetMapping("/sellerList")
    public String sellerList(Model model){
        model.addAttribute("page", "sellerList");
        return "dashboard";
    }

    @GetMapping("/customerList")
    public String customerList(Model model){
        model.addAttribute("page", "customerList");
        return "dashboard";
    }
}
