package com.kdu.security.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDTO {
    String username;
    String password;
}
