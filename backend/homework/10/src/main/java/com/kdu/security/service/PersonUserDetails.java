package com.kdu.security.service;

import com.kdu.security.dto.request.UserRequestDTO;
import com.kdu.security.dto.response.UserResponseDTO;
import com.kdu.security.service.UserService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class PersonUserDetails implements UserDetailsService {

    private final UserService service;

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public PersonUserDetails(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDTO user = service.getUserByName(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        authorities.forEach(authority -> log.info("Role assigned: " + authority.getAuthority()));
        return new User(user.getUserName(), passwordEncoder().encode(user.getPassword()), authorities);
    }


    // adding users from app.properties for testing
    @Value("${user.khush.username}")
    private String khushUsername;

    @Value("${user.khush.password}")
    private String khushPassword;

    @Value("${user.khush.email}")
    private String khushEmail;

    @Value("${user.khush.role}")
    private String khushRole;

    @Value("${user.kp18.username}")
    private String kp18Username;

    @Value("${user.kp18.password}")
    private String kp18Password;

    @Value("${user.kp18.email}")
    private String kp18Email;

    @Value("${user.kp18.role}")
    private String kp18Role;

    @PostConstruct
    public void init() {
        service.addUser(new UserRequestDTO(khushUsername, passwordEncoder().encode(khushPassword), khushEmail, khushRole));
        service.addUser(new UserRequestDTO(kp18Username, passwordEncoder().encode(kp18Password), kp18Email, kp18Role));
    }

}