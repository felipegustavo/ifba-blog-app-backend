package br.edu.ifba.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.blogapp.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

}
