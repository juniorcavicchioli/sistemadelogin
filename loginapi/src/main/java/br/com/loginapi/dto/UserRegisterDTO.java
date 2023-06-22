package br.com.loginapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
