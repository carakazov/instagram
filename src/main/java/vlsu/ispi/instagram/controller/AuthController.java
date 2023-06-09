package vlsu.ispi.instagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlsu.ispi.instagram.dto.auth.AuthRequestDto;
import vlsu.ispi.instagram.dto.auth.AuthResponseDto;
import vlsu.ispi.instagram.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    @PreAuthorize("permitAll()")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        return authService.auth(request);
    }
}
