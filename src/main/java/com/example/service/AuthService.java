package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AuthDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MailSenderService mailSenderService;

    public ApiResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()){
            ProfileEntity entity = new ProfileEntity();
            entity.setId(UUID.randomUUID());
            entity.setEmail(dto.getEmail());
            entity.setPassword(MD5Util.encode(dto.getPassword()));
            entity.setStatus(ProfileStatus.REGISTRATION);
            entity.setRole(ProfileRole.ROLE_CUSTOMER);
            profileRepository.save(entity);
            mailSenderService.sendEmailVerification(entity.getEmail(), entity.getName(), entity.getId());
            return new ApiResponseDTO(true, "The verification link was send to email.");
        }

        ProfileEntity entity = optional.get();
        if (!entity.getPassword().equals(dto.getPassword())){
            throw new AppBadRequestException("Wrong password");
        }

        dto.setJwt(JWTUtil.encode(entity.getEmail(), entity.getRole()));
        return new ApiResponseDTO(true, "Success");

    }

    public ApiResponseDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Email not found");
        }
        return new ApiResponseDTO(true, "Profile found");
    }

    public ApiResponseDTO emailVerification(String jwt) {
        JwtDTO jwtDTO = JWTUtil.decodeEmailJwt(jwt);
        Optional<ProfileEntity> exists = profileRepository.findById(jwtDTO.getId());
        if (exists.isEmpty()) {
            throw new AppBadRequestException("Profile not found");
        }

        ProfileEntity entity = exists.get();
        if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadRequestException("Wrong status");
        }

        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity); // update
        return new ApiResponseDTO(true, "Registration completed");
    }
}
