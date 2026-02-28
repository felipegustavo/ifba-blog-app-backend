package br.edu.ifba.blogapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blogapp.domain.dto.CategoryDTO;
import br.edu.ifba.blogapp.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final List<CategoryDTO> categories = new ArrayList<>();

    @PostMapping
    public CategoryDTO create(@RequestBody @Valid CategoryDTO cat) {
        Long id = Math.abs(Random.from(RandomGenerator.getDefault()).nextLong());
        cat.setId(id);
        categories.add(cat);
        return cat;
    }
    
    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id, @RequestBody @Valid CategoryDTO cat) {
        CategoryDTO c = getById(id);
        c.setName(cat.getName());
        c.setDescription(cat.getDescription());
        return c;
    }

    @GetMapping
    public List<CategoryDTO> getAll(@RequestParam(required = false) String search) {
        if (search == null) {
            return categories;
        }

        return categories
            .stream()
            .filter(c -> c.getName().toLowerCase().contains(search.toLowerCase()))
            .toList();
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        var opt = categories.stream().filter(c -> c.getId().equals(id)).findFirst();
        return opt.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        CategoryDTO c = getById(id);
        categories.remove(c);
        return ResponseEntity.noContent().build();
    }

}
