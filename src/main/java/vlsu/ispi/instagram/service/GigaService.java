package vlsu.ispi.instagram.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import vlsu.ispi.instagram.dto.AddPostDto;
import vlsu.ispi.instagram.dto.ProfileDto;
import vlsu.ispi.instagram.dto.ReadPostDto;
import vlsu.ispi.instagram.dto.RegistrationDto;

public interface GigaService {
    void register(RegistrationDto request);

    ProfileDto getProfile(UUID externalId);

    void addPost(AddPostDto request, List<MultipartFile> photos);

    ReadPostDto readPost(UUID postExternalId);

    void likePost(UUID postExternalId);
}
