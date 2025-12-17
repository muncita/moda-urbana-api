package com.example.moda_urbana_api.controller;

import com.example.moda_urbana_api.model.Product;
import com.example.moda_urbana_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


import java.util.List;


@Tag(
    name = "Products",
    description = "Endpoints para la gestión de productos. Incluye listado, búsqueda, creación, actualización y eliminación lógica."
)
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
    summary = "Listar productos activos",
    description = "Retorna todos los productos que se encuentran activos (active = true)."
)
    @GetMapping
    public List<Product> getAll() {
        return productService.findAllActive();
    }

    @Operation(
    summary = "Obtener producto por ID",
    description = "Retorna un producto según su ID. No valida si el producto está activo."
)
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(
    summary = "Crear producto",
    description = "Crea un nuevo producto. El ID se genera automáticamente y el producto se guarda como activo."
)
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @Operation(
    summary = "Actualizar producto",
    description = "Actualiza los datos de un producto existente identificado por su ID."
)
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @Operation(
    summary = "Eliminar producto",
    description = "Elimina un producto de forma lógica, marcándolo como inactivo (active = false)."
)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
