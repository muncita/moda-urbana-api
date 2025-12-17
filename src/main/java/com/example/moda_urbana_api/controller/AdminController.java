package com.example.moda_urbana_api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Tag(name = "Admin", description = "Endpoints restringidos a ADMIN")
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Ping admin", description = "Solo accesible con rol ADMIN.")
    @GetMapping("/ping")
    @PreAuthorize("hasRole('ADMIN')")
    public String pingAdmin() {
        return "Solo los admin pueden ver esto 👑";
    }
}
