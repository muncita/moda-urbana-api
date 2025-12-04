package com.example.moda_urbana_api.config;

import com.example.moda_urbana_api.model.Role;
import com.example.moda_urbana_api.model.User;
import com.example.moda_urbana_api.repository.RoleRepository;
import com.example.moda_urbana_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crear roles si no existen
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("ROLE_USER").build()
                ));

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("ROLE_ADMIN").build()
                ));

        // Crear usuario admin si no existe
        if (!userRepository.existsByEmail("admin@moda.com")) {
            User admin = User.builder()
                    .email("admin@moda.com")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("Admin Moda")
                    .enabled(true)
                    .roles(Set.of(roleUser, roleAdmin))
                    .build();

            userRepository.save(admin);

            System.out.println("✅ Usuario admin creado: admin@moda.com / admin123");
        }
    }
}
