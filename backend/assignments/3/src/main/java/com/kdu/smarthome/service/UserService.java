package com.kdu.smarthome.service;

import com.kdu.smarthome.exception.EntityNotFoundException;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for user-related operations, implementing Spring Security's UserDetailsService interface.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository The repository for user data.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username.
     *
     * @param username The username of the user whose details are to be loaded.
     * @return         The UserDetails object representing the user.
     * @throws UsernameNotFoundException If the specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new EntityNotFoundException("User not Found"));
    }
}
