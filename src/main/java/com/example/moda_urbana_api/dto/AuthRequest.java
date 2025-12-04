package com.example.moda_urbana_api.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
