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
import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MailSenderService mailSenderService;

    private Random random = new Random();

    public AuthDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getUsername());
        if (optional.isEmpty()) {
            ProfileEntity entity = new ProfileEntity();
            entity.setId(UUID.randomUUID());
            entity.setEmail(dto.getUsername());
            entity.setStatus(ProfileStatus.REGISTRATION);
            entity.setRole(ProfileRole.ROLE_CUSTOMER);
            profileRepository.save(entity);
            return dto;
        }

        return null;

    }

    public ApiResponseDTO registration(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()) {
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

    public Integer profileAuthorization(AuthDTO dto) {
        Integer currentCode = random.nextInt(1000, 10000);
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getUsername());
        if (optional.isEmpty()) {
            ProfileEntity entity = new ProfileEntity();
            entity.setId(UUID.randomUUID());
            entity.setEmail(dto.getUsername());
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setRole(ProfileRole.ROLE_CUSTOMER);
            entity.setPassword(String.valueOf(currentCode));
            profileRepository.save(entity);
        } else {
            profileRepository.updatePassword(dto.getUsername(), String.valueOf(currentCode));
        }
        mailSenderService.sendEmailVerification(dto.getUsername(), currentCode);
        return 1;
    }
}
