package br.edu.ifba.blogapp.controller;

import static br.edu.ifba.blogapp.domain.consts.PathConstant.POST_PATH;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blogapp.domain.dto.PostDTO;
import br.edu.ifba.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(POST_PATH)
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping
    public PostDTO create(@RequestBody @Valid PostDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PostDTO update(@PathVariable Long id, @RequestBody @Valid PostDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public Page<PostDTO> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10) Pageable pageable) {

        if (keyword != null && !keyword.isBlank()) {
            return service.searchByTitleKeyword(keyword, pageable);
        }

        if (userId != null) {
            return service.getByUserId(userId, pageable);
        }

        if (categoryId != null) {
            return service.getByCategoryId(categoryId, pageable);
        }

        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
