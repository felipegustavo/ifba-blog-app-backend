package br.edu.ifba.blogapp.domain.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_POST")
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 300)
    private String title;
    
    @Column(name = "corpo", nullable = false)
    private String body;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate criationDate;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<CommentEntity> comments;

}
