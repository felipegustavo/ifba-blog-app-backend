package br.edu.ifba.blogapp.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPasswordUpdateDTO(
        @NotBlank
        String currentPassword,

        @NotBlank
        @Size(min = 6, max = 255)
        String newPassword) {
}
