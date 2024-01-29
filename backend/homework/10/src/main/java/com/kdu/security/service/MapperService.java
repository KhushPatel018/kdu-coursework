package com.kdu.security.service;

import com.kdu.security.dto.request.UserRequestDTO;
import com.kdu.security.dto.response.UserResponseDTO;
import com.kdu.security.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    public UserResponseDTO toDTO(Person user) {
        return UserResponseDTO.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    public Person toEntity(UserRequestDTO userDTO) {
        return Person.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
    }
}
