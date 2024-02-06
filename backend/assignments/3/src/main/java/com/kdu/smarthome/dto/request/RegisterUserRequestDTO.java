package com.kdu.smarthome.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequestDTO {
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String emailId;
}
