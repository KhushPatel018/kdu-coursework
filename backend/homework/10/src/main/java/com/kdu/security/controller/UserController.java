package com.kdu.security.controller;

import com.kdu.security.dto.response.UserResponseDTO;
import com.kdu.security.dto.request.UserRequestDTO;
import com.kdu.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDTO> getUserByName(@RequestParam String name) {
        return new ResponseEntity<>(service.getUserByName(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> postUser(@RequestBody UserRequestDTO user) {
        service.addUser(user);
        return new ResponseEntity<>("new user added with name : " + user.getUserName(), HttpStatus.CREATED);
    }
}
