package br.edu.ifba.blogapp.mapper;

import org.springframework.stereotype.Component;

import br.edu.ifba.blogapp.domain.dto.CommentDTO;
import br.edu.ifba.blogapp.domain.entity.CommentEntity;
import br.edu.ifba.blogapp.domain.entity.PostEntity;
import br.edu.ifba.blogapp.domain.entity.UserEntity;

@Component
public class CommentMapper {

    public CommentDTO toDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CommentDTO(
                entity.getId(),
                entity.getBody(),
                entity.getRating(),
                entity.getPost() != null ? entity.getPost().getId() : null,
                entity.getUser() != null ? entity.getUser().getId() : null);
    }

    public CommentEntity toEntity(CommentDTO dto, PostEntity post, UserEntity user) {
        if (dto == null) {
            return null;
        }

        var entity = new CommentEntity();
        entity.setId(dto.getId());
        entity.setBody(dto.getBody());
        entity.setRating(dto.getRating());
        entity.setPost(post);
        entity.setUser(user);
        return entity;
    }

    public void updateEntity(CommentEntity entity, CommentDTO dto, PostEntity post, UserEntity user) {
        entity.setBody(dto.getBody());
        entity.setRating(dto.getRating());
        entity.setPost(post);
        entity.setUser(user);
    }

}
