package com.example.moda_urbana_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<com.example.moda_urbana_api.model.Product, Long> {

    // Devuelve todos los productos activos
    List<com.example.moda_urbana_api.model.Product> findByActiveTrue();

    // Devuelve productos activos filtrando por categoría (case-insensitive)
    List<com.example.moda_urbana_api.model.Product> findByCategoryIgnoreCaseAndActiveTrue(String category);
}
