package br.edu.ifba.blogapp.controller;

import br.edu.ifba.blogapp.domain.dto.LoginResponseDTO;
import br.edu.ifba.blogapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}
