package br.edu.ifba.blogapp.controller;

import static br.edu.ifba.blogapp.domain.consts.PathConstant.USER_PATH;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blogapp.domain.dto.UserPasswordUpdateDTO;
import br.edu.ifba.blogapp.domain.dto.UserProfileUpdateDTO;
import br.edu.ifba.blogapp.domain.dto.UserDTO;
import br.edu.ifba.blogapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(USER_PATH)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserDTO create(@RequestBody @Valid UserDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        return service.update(id, dto);
    }

    @PutMapping("/{id}/profile")
    public UserDTO updateProfile(@PathVariable Long id, @RequestBody @Valid UserProfileUpdateDTO dto) {
        return service.updateProfile(id, dto);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UserPasswordUpdateDTO dto) {
        service.updatePassword(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

}
