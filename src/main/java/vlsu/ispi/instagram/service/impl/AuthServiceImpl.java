package vlsu.ispi.instagram.service.impl;

import java.util.Objects;

import liquibase.pro.packaged.A;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vlsu.ispi.instagram.config.auth.utils.TokenProvider;
import vlsu.ispi.instagram.dto.auth.AuthRequestDto;
import vlsu.ispi.instagram.dto.auth.AuthResponseDto;
import vlsu.ispi.instagram.model.UserEntity;
import vlsu.ispi.instagram.repository.UserRepository;
import vlsu.ispi.instagram.service.AuthService;
import vlsu.ispi.instagram.utils.exception.AuthException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    public AuthResponseDto auth(AuthRequestDto request) {
        UserEntity user = userRepository.findByLogin(request.getLogin());
        if(Objects.isNull(user)) {
            throw new AuthException();
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException();
        }
        return new AuthResponseDto(tokenProvider.provide(user));
    }
}
