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
    private String description;     // Descripción

    @Column(nullable = false)
    private Double price;           // Precio (double para simplificar)

    @Column(nullable = false)
    private String category;        // Categoría (ej: "Camisetas", "Pantalones")

    private String imageUrl;        // URL imagen

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;  // Si el producto está activo
}
