package br.edu.ifba.blogapp.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifba.blogapp.domain.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "user", "post" })
    Optional<CommentEntity> findById(Long id);

    @EntityGraph(attributePaths = { "user", "post" })
    @Query("select ce from CommentEntity ce order by ce.creationDate desc")
    List<CommentEntity> findAllByCreationDateDesc();

    @EntityGraph(attributePaths = { "user", "post" })
    @Query("select ce from CommentEntity ce where ce.post.id = :postId order by ce.creationDate desc")
    List<CommentEntity> findCommentsByPostId(@Param("postId") Long postId);

}
