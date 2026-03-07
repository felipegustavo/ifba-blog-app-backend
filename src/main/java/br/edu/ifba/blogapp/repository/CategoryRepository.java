package br.edu.ifba.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifba.blogapp.domain.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("select ce from CategoryEntity ce where ce.name like %:name%")
    List<CategoryEntity> findByNameLike(@Param("name") String name);

}
