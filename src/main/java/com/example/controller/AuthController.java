package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String log(Model model) {
        model.addAttribute("profile", new AuthDTO());
        model.addAttribute("sendCode", false);
        return "login";
    }

    @PostMapping("/getCode")
    public String getCode(@ModelAttribute AuthDTO profile, Model model) {
        int b = authService.profileAuthorization(profile);
        model.addAttribute("profile", profile);
        model.addAttribute("sendCode", true);
        return "login";
    }


//
//    @PostMapping("/registration")
//    public ResponseEntity<ApiResponseDTO> registration(@RequestBody ProfileDTO dto) {
//        return ResponseEntity.ok(authService.registration(dto));
//    }
//

}
