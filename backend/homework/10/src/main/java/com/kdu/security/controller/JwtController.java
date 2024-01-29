package com.kdu.security.controller;

import com.kdu.security.security.CustomAuthenticationManager;
import com.kdu.security.dto.request.LoginRequestDTO;
import com.kdu.security.dto.response.LoginResponseDTO;
import com.kdu.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class JwtController {


    private final TokenService tokenService;
    private final CustomAuthenticationManager authenticationManager;

    public JwtController(TokenService tokenService, CustomAuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public LoginResponseDTO token(@RequestBody LoginRequestDTO userLogin) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        log.info("Token Requested By " + userLogin.getUsername());
        return new LoginResponseDTO(tokenService.generateToken(authentication));
    }
}

