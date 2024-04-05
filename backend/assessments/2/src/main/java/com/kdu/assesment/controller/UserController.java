package com.kdu.assesment.controller;



import com.kdu.assesment.entity.User;
import com.kdu.assesment.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing User entities.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Created with name " + user.getUsername() + " Role : " + user.getRole());
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userService.getAll());
    }

}
