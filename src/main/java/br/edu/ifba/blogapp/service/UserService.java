package br.edu.ifba.blogapp.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifba.blogapp.domain.dto.UserPasswordUpdateDTO;
import br.edu.ifba.blogapp.domain.dto.UserProfileUpdateDTO;
import br.edu.ifba.blogapp.domain.dto.UserDTO;
import br.edu.ifba.blogapp.exceptions.DuplicateEmailException;
import br.edu.ifba.blogapp.exceptions.InvalidPasswordException;
import br.edu.ifba.blogapp.exceptions.ResourceNotFoundException;
import br.edu.ifba.blogapp.mapper.UserMapper;
import br.edu.ifba.blogapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO create(UserDTO dto) {
        validateDuplicateEmailForCreate(dto.getEmail());
        var entity = mapper.toEntity(dto);
        entity.setId(null);
        entity.setRole("USER");
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(entity));
    }

    public UserDTO update(Long id, UserDTO dto) {
        validateDuplicateEmailForUpdate(id, dto.getEmail());
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateEntity(existing, dto);
        existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(existing));
    }

    public UserDTO updateProfile(Long id, UserProfileUpdateDTO dto) {
        validateDuplicateEmailForUpdate(id, dto.email());
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        existing.setName(dto.name());
        existing.setEmail(dto.email());
        return mapper.toDto(repository.save(existing));
    }

    public void updatePassword(Long id, UserPasswordUpdateDTO dto) {
        var existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        if (!passwordEncoder.matches(dto.currentPassword(), existing.getPassword())) {
            throw new InvalidPasswordException();
        }
        existing.setPassword(passwordEncoder.encode(dto.newPassword()));
        repository.save(existing);
    }

    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public UserDTO getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private void validateDuplicateEmailForCreate(String email) {
        if (repository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }
    }

    private void validateDuplicateEmailForUpdate(Long id, String email) {
        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateEmailException(email);
        }
    }

}
