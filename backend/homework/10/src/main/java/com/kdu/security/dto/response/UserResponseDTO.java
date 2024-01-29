package com.kdu.security.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDTO {
    private String userName;
    private String password;
    private String role;
    private String email;
}
