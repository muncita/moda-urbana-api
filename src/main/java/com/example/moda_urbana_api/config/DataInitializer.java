package com.example.moda_urbana_api.config;

import com.example.moda_urbana_api.model.Role;
import com.example.moda_urbana_api.model.User;
import com.example.moda_urbana_api.model.Product;
import com.example.moda_urbana_api.repository.RoleRepository;
import com.example.moda_urbana_api.repository.UserRepository;
import com.example.moda_urbana_api.repository.ProductRepository;

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
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // ===== ROLES =====
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("ROLE_USER").build()
                ));

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("ROLE_ADMIN").build()
                ));

        // ===== USUARIO ADMIN =====
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

        // ===== PRODUCTOS =====
        if (productRepository.count() == 0) {

            List<Product> products = List.of(
                    // 1
                    Product.builder()
                            .name("Poleron Negro Fatto")
                            .description("Polerón oversize negro estilo streetwear.")
                            .price(69990)
                            .category("Polerones")
                            .imageUrl("https://media.istockphoto.com/id/1675347103/es/foto/camiseta-blanca-aislada-sobre-fondo-blanco-lista-para-su-dise%C3%B1o-o-logotipo.jpg?s=612x612&w=0&k=20&c=j8d5Ry9jsE5CNVHhlbH5XCTUaYRhm7r27ZraRoVeYiw=")
                            .active(true)
                            .build(),

                    // 2
                    Product.builder()
                            .name("Poleron Oversize Camo")
                            .description("Polerón oversize con estilo camuflado.")
                            .price(59990)
                            .category("Polerones")
                            .imageUrl("https://m.media-amazon.com/images/I/61jwUv7DpcL._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 3
                    Product.builder()
                            .name("Camiseta Estampada Vintage")
                            .description("Camiseta con diseño vintage urbano.")
                            .price(24990)
                            .category("Camisetas")
                            .imageUrl("https://m.media-amazon.com/images/I/71cQ7O4Z3mL._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 4
                    Product.builder()
                            .name("Camiseta Basic Blanca")
                            .description("Camiseta básica para combinar cualquier outfit.")
                            .price(14990)
                            .category("Camisetas")
                            .imageUrl("https://m.media-amazon.com/images/I/51AUXhelH0L._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 5
                    Product.builder()
                            .name("Polera Estilo Street")
                            .description("Polera gráfica estilo urbano.")
                            .price(18990)
                            .category("Poleras")
                            .imageUrl("https://m.media-amazon.com/images/I/61wHzKz7LML._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 6
                    Product.builder()
                            .name("Polera Oversize Blanca")
                            .description("Polera oversize ideal para outfits minimalistas.")
                            .price(17990)
                            .category("Poleras")
                            .imageUrl("https://m.media-amazon.com/images/I/61g9l2GjEHL._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 7
                    Product.builder()
                            .name("Pantalón Cargo Negro")
                            .description("Cargo estilo urbano con bolsillos laterales.")
                            .price(39990)
                            .category("Pantalones")
                            .imageUrl("https://m.media-amazon.com/images/I/71QDK9RlDD._AC_UY1000_.jpg")
                            .active(true)
                            .build(),

                    // 8
                    Product.builder()
                            .name("Pantalón Loose Fit")
                            .description("Pantalón suelto estilo urbano para uso diario.")
                            .price(32990)
                            .category("Pantalones")
                            .imageUrl("https://m.media-amazon.com/images/I/71xppkZgaHL._AC_UY1000_.jpg")
                            .active(true)
                            .build()
            );

            productRepository.saveAll(products);
            System.out.println("✅ Productos iniciales creados: " + products.size());
        }
    }
}
