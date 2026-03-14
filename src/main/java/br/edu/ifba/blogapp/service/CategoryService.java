package br.edu.ifba.blogapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public CategoryDTO create(CategoryDTO dto) {
        var entity = mapper.toEntity(dto);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    public CategoryDTO update(Long id, CategoryDTO dto) {
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        return mapper.toDto(repository.save(existing));
    }

    public List<CategoryDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public List<CategoryDTO> searchByName(String name) {
        return repository.findByNameLike(name).stream().map(mapper::toDto).toList();
    }

    public CategoryDTO getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
