package br.edu.ifba.blogapp.controller;

import static br.edu.ifba.blogapp.domain.consts.PathConstant.COMMENT_PATH;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blogapp.domain.dto.CommentDTO;
import br.edu.ifba.blogapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(COMMENT_PATH)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public CommentDTO create(@RequestBody @Valid CommentDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CommentDTO update(@PathVariable Long id, @RequestBody @Valid CommentDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public List<CommentDTO> getAll(@RequestParam(required = false) Long postId) {
        if (postId != null) {
            return service.getByPostId(postId);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
