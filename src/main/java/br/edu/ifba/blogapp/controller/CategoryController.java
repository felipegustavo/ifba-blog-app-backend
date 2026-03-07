package br.edu.ifba.blogapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blogapp.domain.dto.CategoryDTO;
import br.edu.ifba.blogapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import static br.edu.ifba.blogapp.domain.consts.PathConstant.CATEGORY_PATH;

@RestController
@RequestMapping(CATEGORY_PATH)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public CategoryDTO create(@RequestBody @Valid CategoryDTO cat) {
        return service.save(cat, null);
    }
    
    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id, @RequestBody @Valid CategoryDTO cat) {
        return service.save(cat, id);
    }

    @GetMapping
    public List<CategoryDTO> getAll(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return service.getAll();
        }
        return service.searchByName(search);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
