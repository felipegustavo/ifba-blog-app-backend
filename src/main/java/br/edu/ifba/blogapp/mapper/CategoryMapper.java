package br.edu.ifba.blogapp.mapper;

import org.springframework.stereotype.Component;

import br.edu.ifba.blogapp.domain.dto.CategoryDTO;
import br.edu.ifba.blogapp.domain.entity.CategoryEntity;

@Component
public class CategoryMapper {

    public CategoryDTO toDto(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CategoryDTO(entity.getId(), entity.getName(), entity.getDescription());
    }
    
    public CategoryEntity toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        return new CategoryEntity(dto.getId(), dto.getName(), dto.getDescription(), null);
    }

}
