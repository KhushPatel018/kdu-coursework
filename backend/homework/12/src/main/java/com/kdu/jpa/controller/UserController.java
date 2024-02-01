package com.kdu.jpa.controller;

import com.kdu.jpa.entity.User;
import com.kdu.jpa.service.UserService;
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

    /**
     * Constructs a new UserController with the provided UserService.
     *
     * @param userService The UserService to be used by the controller.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handles HTTP POST requests to create a new User.
     *
     * @param user The User object to be added.
     * @return ResponseEntity with a success message and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all Users for a specific tenant.
     *
     * @param id The UUID of the tenant.
     * @return ResponseEntity with a list of Users and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> getAllUsersForTenant(@PathVariable UUID id) {
        List<User> users = userService.getAllUsersForTenant(id);
        return ResponseEntity.ok(users);
    }

    /**
     * Handles HTTP PUT requests to update a User by ID.
     *
     * @param id   The UUID of the User to be updated.
     * @param user The updated User object.
     * @return ResponseEntity with a success message and HTTP status code 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests to retrieve paginated Users.
     *
     * @param pageNo The page number.
     * @param size   The page size.
     * @return Page of Users with pagination information.
     */
    @GetMapping("/getAll")
    public Page<User> getAllPaginatedUsers(@RequestParam int pageNo, @RequestParam int size) {
        pageNo = Math.max(0, pageNo);
        size = Math.min(50, Math.max(1, size));
        PageRequest pageable = PageRequest.of(pageNo, size);
        return userService.getAllPaginatedUsers(pageable);
    }
}
