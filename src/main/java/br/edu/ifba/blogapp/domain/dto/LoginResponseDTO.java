package br.edu.ifba.blogapp.domain.dto;

public record LoginResponseDTO(
        Long userId,
        String name,
        String email,
        String role,
        String token,
        String tokenType
) {
}
