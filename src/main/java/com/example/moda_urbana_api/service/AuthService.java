package com.example.moda_urbana_api.service;

import com.example.moda_urbana_api.dto.*;
import com.example.moda_urbana_api.model.Role;
import com.example.moda_urbana_api.model.User;
import com.example.moda_urbana_api.repository.RoleRepository;
import com.example.moda_urbana_api.repository.UserRepository;
import com.example.moda_urbana_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("ROLE_USER").build()
                ));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .roles(Set.of(userRole))
                .enabled(true)
                .build();

        userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", user.getFullName());
        claims.put("roles", List.of("ROLE_USER"));

        String token = jwtService.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", user.getFullName());
        claims.put("roles", roles);

        String token = jwtService.generateToken(user.getEmail(), claims);

        return new AuthResponse(token);
    }

    public UserProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(
                java.util.stream.Collectors.toSet()
        );

        return UserProfileDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(roles)
                .build();
    }
}
