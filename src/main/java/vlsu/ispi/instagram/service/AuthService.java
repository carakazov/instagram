package vlsu.ispi.instagram.service;

import vlsu.ispi.instagram.dto.auth.AuthRequestDto;
import vlsu.ispi.instagram.dto.auth.AuthResponseDto;

public interface AuthService {
    AuthResponseDto auth(AuthRequestDto request);
}
