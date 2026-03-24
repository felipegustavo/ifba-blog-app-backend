package br.edu.ifba.blogapp.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.blogapp.domain.dto.AuthenticatedUser;
import br.edu.ifba.blogapp.domain.dto.LoginResponseDTO;
import br.edu.ifba.blogapp.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder encoder;

    public LoginResponseDTO generateToken(Authentication authentication) {
        var now = Instant.now();
        var expiry = 3600L;

        List<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserEntity u = ((AuthenticatedUser) authentication.getPrincipal()).getUser();

        var claims = JwtClaimsSet
                .builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("roles", roles)
                .claim("id", u.getId())
                .build();

        var token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getRole(), token, "Bearer");
    }

}

