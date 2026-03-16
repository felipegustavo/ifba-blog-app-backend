package br.edu.ifba.blogapp.domain.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotBlank
    private String body;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer rating;

    @NotNull
    private Long postId;

    @NotNull
    private Long userId;

    private LocalDateTime creationDate;

    private UserDTO user;

}
