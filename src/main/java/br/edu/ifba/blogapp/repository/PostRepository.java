package br.edu.ifba.blogapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifba.blogapp.domain.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    String ACCENTED_CHARS = "áàãâäéèêëíìîïóòõôöúùûüç";
    String NORMALIZED_CHARS = "aaaaaeeeeiiiiooooouuuuc";

    @Query("select pe from PostEntity pe")
    Page<PostEntity> findAllPosts(Pageable pageable);

    @Query("select pe from PostEntity pe where pe.user.id = :userId")
    Page<PostEntity> findPostsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("select pe from PostEntity pe where pe.category.id = :categoryId")
    Page<PostEntity> findPostsByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query(value = "select * from tb_post pe "
            + "where translate(lower(pe.titulo), '" + ACCENTED_CHARS + "', '" + NORMALIZED_CHARS + "') "
            + "like concat('%', translate(lower(:keyword), '" + ACCENTED_CHARS + "', '" + NORMALIZED_CHARS
            + "'), '%')",
            countQuery = "select count(*) from tb_post pe "
                    + "where translate(lower(pe.titulo), '" + ACCENTED_CHARS + "', '" + NORMALIZED_CHARS + "') "
                    + "like concat('%', translate(lower(:keyword), '" + ACCENTED_CHARS + "', '"
                    + NORMALIZED_CHARS + "'), '%')",
            nativeQuery = true)
    Page<PostEntity> findPostsByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);

}
