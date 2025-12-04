package com.example.moda_urbana_api.controller;

import com.example.moda_urbana_api.model.Product;
import com.example.moda_urbana_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.findAllActive();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

<<<<<<< HEAD
    // SOLO si quieres, puedes proteger estos con rol admin:
    // @PreAuthorize("hasRole('ADMIN')")
=======
>>>>>>> 7b955640b304eee1cce8237156ecc9179215078d
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

<<<<<<< HEAD
    // @PreAuthorize("hasRole('ADMIN')")
=======
>>>>>>> 7b955640b304eee1cce8237156ecc9179215078d
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

<<<<<<< HEAD
    // @PreAuthorize("hasRole('ADMIN')")
=======
>>>>>>> 7b955640b304eee1cce8237156ecc9179215078d
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
