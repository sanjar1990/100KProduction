package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody AuthDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponseDTO> registration(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }

    @PostMapping("/verification/email/{jwt}")
    public ResponseEntity<ApiResponseDTO> emailVerification(@PathVariable String jwt){
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}
