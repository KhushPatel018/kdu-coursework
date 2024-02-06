package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.RegisterUserRequestDTO;
import com.kdu.smarthome.dto.response.UserResponseDTO;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final MapperService mapperService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenService tokenService, MapperService mapperService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mapperService = mapperService;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO register(RegisterUserRequestDTO registerUserRequestDTO) {
        log.info("started...............");
        registerUserRequestDTO.setPassword(passwordEncoder.encode(registerUserRequestDTO.getPassword()));
        User user = userRepository.save(mapperService.toEntity(registerUserRequestDTO));
        log.info(registerUserRequestDTO.toString());
        String token = tokenService.generateToken(user);
        log.info("=============================================");
        log.info(token);
        return new UserResponseDTO("User registered successfully", token);
    }
}
