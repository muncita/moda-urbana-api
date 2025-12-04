package com.example.moda_urbana_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;            // Nombre del producto

    @Column(length = 1000)
    private String description;     // Descripción del producto

    @Column(nullable = false)
    private Integer price;          // Precio en CLP

    @Column(nullable = false)
    private String category;        // Categoría (ej: "Camisetas", "Pantalones")

    private String imageUrl;        // URL de la imagen

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;  // Producto activo o no
}
