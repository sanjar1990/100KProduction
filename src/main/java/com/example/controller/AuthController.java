package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String log(Model model){
        model.addAttribute("profile", new AuthDTO());
        return "login";
    }

    @PostMapping("/getCode")
    public String getCode(@ModelAttribute AuthDTO profile, Model model){
        int b = authService.getCode(profile);

        if (b == 1){
            model.addAttribute("profile", profile);
            model.addAttribute("sendCode", true);
            return "login";
        }else if (b == 2){
            model.addAttribute("profile", profile);
            model.addAttribute("sendCode", true);
            model.addAttribute("isError", true);
            return "login";
        }
        return "index";
    }
    @PostMapping("/saveLogin")
    public AuthDTO login(@RequestBody AuthDTO dto){
        return authService.login(dto);
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponseDTO> registration(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }


}
