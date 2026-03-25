package br.edu.ifba.blogapp.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserProfileUpdateDTO(
        @NotBlank
        @Size(min = 3, max = 150)
        String name,

        @NotBlank
        @Email
        @Size(max = 150)
        String email) {
}
