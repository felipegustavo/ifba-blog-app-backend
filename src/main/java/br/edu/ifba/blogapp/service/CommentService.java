package br.edu.ifba.blogapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.blogapp.domain.dto.CommentDTO;
import br.edu.ifba.blogapp.exceptions.ResourceNotFoundException;
import br.edu.ifba.blogapp.mapper.CommentMapper;
import br.edu.ifba.blogapp.repository.CommentRepository;
import br.edu.ifba.blogapp.repository.PostRepository;
import br.edu.ifba.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper mapper;

    public CommentDTO create(CommentDTO dto) {
        var post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getPostId()));
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getUserId()));
        var entity = mapper.toEntity(dto, post, user);
        entity.setId(null);
        return mapper.toDto(repository.save(entity));
    }

    public CommentDTO update(Long id, CommentDTO dto) {
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        var post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getPostId()));
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getUserId()));
        mapper.updateEntity(existing, dto, post, user);
        return mapper.toDto(repository.save(existing));
    }

    public List<CommentDTO> getAll() {
        return repository.findAllByCreationDateDesc().stream().map(mapper::toDto).toList();
    }

    public List<CommentDTO> getByPostId(Long postId) {
        return repository.findCommentsByPostId(postId).stream().map(mapper::toDto).toList();
    }

    public CommentDTO getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
