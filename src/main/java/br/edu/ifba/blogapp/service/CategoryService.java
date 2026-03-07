package br.edu.ifba.blogapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifba.blogapp.domain.dto.CategoryDTO;
import br.edu.ifba.blogapp.exceptions.ResourceNotFoundException;
import br.edu.ifba.blogapp.mapper.CategoryMapper;
import br.edu.ifba.blogapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryDTO save(CategoryDTO cat, Long id) {
        var entity = mapper.toEntity(cat);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public List<CategoryDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<CategoryDTO> searchByName(String name) {
        return repository.findByNameLike(name).stream().map(mapper::toDto).toList();
    }

    public CategoryDTO getById(Long id) {
        var cat = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toDto(cat);
    }

    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
