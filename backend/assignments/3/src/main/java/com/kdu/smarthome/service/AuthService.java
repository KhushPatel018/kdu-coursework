package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.RegisterUserRequestDTO;
import com.kdu.smarthome.dto.response.UserResponseDTO;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication-related operations.
 */
@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final MapperService mapperService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for AuthService.
     *
     * @param userRepository   The repository for user data.
     * @param tokenService     The service for handling authentication tokens.
     * @param mapperService    The service for mapping DTOs to entities.
     * @param passwordEncoder  The encoder for hashing passwords.
     */
    public AuthService(UserRepository userRepository, TokenService tokenService, MapperService mapperService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mapperService = mapperService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     *
     * @param registerUserRequestDTO The DTO containing user registration information.
     * @return The response DTO indicating the result of the registration process.
     */
    public UserResponseDTO register(RegisterUserRequestDTO registerUserRequestDTO) {
        registerUserRequestDTO.setPassword(passwordEncoder.encode(registerUserRequestDTO.getPassword()));
        User user = userRepository.save(mapperService.toEntity(registerUserRequestDTO));
        String token = tokenService.generateToken(user);
        return new UserResponseDTO("User registered", token);
    }
}
