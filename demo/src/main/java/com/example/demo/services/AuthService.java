package com.example.demo.services;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.controller.AuthResponse;
import com.example.demo.controller.LoginRequest;
import com.example.demo.controller.RegisterRequest;
import com.example.demo.model.UserRole;
import com.example.demo.model.Users;
import com.example.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest) {
        // TODO Auto-generated method stub
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Users user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .userId(user.getId())
            .build();
    }

    public AuthResponse register (RegisterRequest request) {
        Users user = Users.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .name(request.getPhone())
            .address(request.getAddress())
            .lastName(request.getLastname())
            .number(request.getPhone())
            .dni(request.getDni())
            .email(request.getEmail())
            .role(UserRole.USER)
            .build();
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();

    }
    
}
