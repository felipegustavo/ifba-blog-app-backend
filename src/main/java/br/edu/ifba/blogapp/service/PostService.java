package br.edu.ifba.blogapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifba.blogapp.domain.dto.PostDTO;
import br.edu.ifba.blogapp.exceptions.ResourceNotFoundException;
import br.edu.ifba.blogapp.mapper.PostMapper;
import br.edu.ifba.blogapp.repository.CategoryRepository;
import br.edu.ifba.blogapp.repository.PostRepository;
import br.edu.ifba.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper mapper;

    public PostDTO create(PostDTO dto) {
        var category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getCategoryId()));
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getUserId()));
        var entity = mapper.toEntity(dto, category, user);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    public PostDTO update(Long id, PostDTO dto) {
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        var category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getCategoryId()));
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getUserId()));
        mapper.updateEntity(existing, dto, category, user);
        return mapper.toDto(repository.save(existing));
    }

    public Page<PostDTO> getAll(Pageable pageable) {
        return repository.findAllPosts(pageable).map(mapper::toDto);
    }

    public Page<PostDTO> getByUserId(Long userId, Pageable pageable) {
        return repository.findPostsByUserId(userId, pageable).map(mapper::toDto);
    }

    public Page<PostDTO> getByCategoryId(Long categoryId, Pageable pageable) {
        return repository.findPostsByCategoryId(categoryId, pageable).map(mapper::toDto);
    }

    public Page<PostDTO> searchByTitleKeyword(String keyword, Pageable pageable) {
        return repository.findPostsByTitleKeyword(keyword, pageable).map(mapper::toDto);
    }

    public PostDTO getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
