package br.edu.ifba.blogapp.mapper;

import org.springframework.stereotype.Component;

import br.edu.ifba.blogapp.domain.dto.UserDTO;
import br.edu.ifba.blogapp.domain.entity.UserEntity;

@Component
public class UserMapper {

    public UserDTO toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), null, entity.getRole());
    }

    public UserEntity toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return new UserEntity(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getRole(), null, null);
    }

    public void updateEntity(UserEntity entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
    }

}
