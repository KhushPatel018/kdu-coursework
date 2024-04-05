package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RegisterUserRequestDTO;
import com.kdu.smarthome.dto.response.UserResponseDTO;
import com.kdu.smarthome.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthService authService;

    /**
     * Constructor for UserController.
     *
     * @param authService The service for authentication-related operations.
     */
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for user registration.
     *
     * @param registerUserRequestDTO    The DTO containing information about the user to be registered.
     * @return                          ResponseEntity containing the response DTO.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        try {
            return ResponseEntity.ok(authService.register(registerUserRequestDTO));
        } catch (AuthenticationException e) {
            // Return UNAUTHORIZED status if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
