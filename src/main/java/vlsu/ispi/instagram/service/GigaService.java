package vlsu.ispi.instagram.service;

import java.util.UUID;

import vlsu.ispi.instagram.dto.ProfileDto;
import vlsu.ispi.instagram.dto.RegistrationDto;

public interface GigaService {
    void register(RegistrationDto request);

    ProfileDto getProfile(UUID externalId);
}
