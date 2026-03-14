package br.edu.ifba.blogapp.mapper;

import org.springframework.stereotype.Component;

import br.edu.ifba.blogapp.domain.dto.PostDTO;
import br.edu.ifba.blogapp.domain.entity.CategoryEntity;
import br.edu.ifba.blogapp.domain.entity.PostEntity;
import br.edu.ifba.blogapp.domain.entity.UserEntity;

@Component
public class PostMapper {

    public PostDTO toDto(PostEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PostDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                entity.getCriationDate(),
                entity.getUpdateDate(),
                entity.getCategory() != null ? entity.getCategory().getId() : null,
                entity.getUser() != null ? entity.getUser().getId() : null);
    }

    public PostEntity toEntity(PostDTO dto, CategoryEntity category, UserEntity user) {
        if (dto == null) {
            return null;
        }

        var entity = new PostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        entity.setCriationDate(dto.getCriationDate());
        entity.setUpdateDate(dto.getUpdateDate());
        entity.setCategory(category);
        entity.setUser(user);
        entity.setComments(null);
        return entity;
    }

    public void updateEntity(PostEntity entity, PostDTO dto, CategoryEntity category, UserEntity user) {
        entity.setTitle(dto.getTitle());
        entity.setBody(dto.getBody());
        entity.setCriationDate(dto.getCriationDate());
        entity.setUpdateDate(dto.getUpdateDate());
        entity.setCategory(category);
        entity.setUser(user);
    }

}
