package br.edu.ifba.blogapp.domain.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 300)
    private String title;

    @NotBlank
    private String body;

    private LocalDate criationDate;

    private LocalDate updateDate;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long userId;

}
