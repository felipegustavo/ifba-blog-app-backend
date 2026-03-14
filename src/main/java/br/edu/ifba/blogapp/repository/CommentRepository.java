package br.edu.ifba.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifba.blogapp.domain.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("select ce from CommentEntity ce where ce.post.id = :postId")
    List<CommentEntity> findCommentsByPostId(@Param("postId") Long postId);

}
