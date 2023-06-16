package vlsu.ispi.instagram.controller;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vlsu.ispi.instagram.dto.ProfileDto;
import vlsu.ispi.instagram.dto.RegistrationDto;
import vlsu.ispi.instagram.dto.UserListResponseDto;
import vlsu.ispi.instagram.model.AccessStatus;
import vlsu.ispi.instagram.service.GigaService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final GigaService gigaService;

    @PostMapping
    @PreAuthorize("permitAll()")
    public void register(@RequestBody RegistrationDto request) {
        gigaService.register(request);
    }

    @GetMapping("/{externalId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ProfileDto getProfile(@PathVariable(name = "externalId") UUID externalId) {
        return gigaService.getProfile(externalId);
    }

    @PutMapping("/blacklist/{externalId}")
    @Secured("ROLE_USER")
    public void blacklist(@PathVariable(name = "externalId") UUID externalId) {
        gigaService.blackList(externalId);
    }

    @PutMapping("/status/{externalId}")
    public void changeStatus(@PathVariable(name = "externalId") UUID externalId, @RequestParam(name = "status") AccessStatus accessStatus) {
        gigaService.changeAccess(externalId, accessStatus);
    }

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public UserListResponseDto getAllUsers() {
        return gigaService.getAllUsers();
    }
}
