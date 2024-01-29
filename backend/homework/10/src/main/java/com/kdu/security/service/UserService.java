package com.kdu.security.service;

import com.kdu.security.dto.request.UserRequestDTO;
import com.kdu.security.dto.response.UserResponseDTO;
import com.kdu.security.entity.Person;
import com.kdu.security.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserDAO repository;
    private final MapperService mapperService;

    public UserService(UserDAO repository, MapperService mapperService) {
        this.repository = repository;
        this.mapperService = mapperService;
    }

    public List<UserResponseDTO> getAll() {
        if (repository.getAll().isEmpty()) {
            log.info("No users found");
        }
        List<UserResponseDTO> responses = new ArrayList<>();
        for (Person user : repository.getAll()) {
            responses.add(mapperService.toDTO(user));
        }
        return responses;
    }

    public UserResponseDTO getUserByName(String name) {
        Optional<Person> user = repository.getUserByName(name);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(name);
        }
        return mapperService.toDTO(user.get());
    }

    public void addUser(UserRequestDTO user) {
        log.info("User Added");
        repository.addUser(mapperService.toEntity(user));
    }
}
