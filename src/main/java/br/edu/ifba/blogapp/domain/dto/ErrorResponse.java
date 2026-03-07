package br.edu.ifba.blogapp.domain.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String title;
    private String description;
    private Map<String, List<String>> fieldErrors;

}
