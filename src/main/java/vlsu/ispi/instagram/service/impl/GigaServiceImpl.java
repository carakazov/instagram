package vlsu.ispi.instagram.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vlsu.ispi.instagram.dto.AddPostDto;
import vlsu.ispi.instagram.dto.ProfileDto;
import vlsu.ispi.instagram.dto.RegistrationDto;
import vlsu.ispi.instagram.model.*;
import vlsu.ispi.instagram.repository.PhotoRepository;
import vlsu.ispi.instagram.repository.PostRepository;
import vlsu.ispi.instagram.repository.RoleRepository;
import vlsu.ispi.instagram.repository.UserRepository;
import vlsu.ispi.instagram.service.GigaService;
import vlsu.ispi.instagram.utils.SecurityContextHelper;
import vlsu.ispi.instagram.utils.exception.ExceptionCode;
import vlsu.ispi.instagram.utils.exception.GigaException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GigaServiceImpl implements GigaService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhotoRepository photoRepository;
    private final PostRepository postRepository;

    @Override
    public void register(RegistrationDto request) {
        if(userRepository.existsByLogin(request.getLogin())) {
            throw new GigaException(ExceptionCode.WRONG_LOGIN);
        }
        RoleEntity role = roleRepository.findByRole(Role.ROLE_USER);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity user = new UserEntity()
            .setLogin(request.getLogin())
            .setPassword(encodedPassword)
            .setName(request.getName())
            .setSurname(request.getSurname())
            .setMiddleName(request.getMiddleName())
            .setBirthdate(request.getBirthdate())
            .setExternalId(UUID.randomUUID())
            .setAccessStatus(AccessStatus.FULL)
            .setRole(role);
        userRepository.save(user);
    }

    @Override
    public ProfileDto getProfile(UUID externalId) {
        UserEntity user = userRepository.findByExternalId(externalId);
        return new ProfileDto()
            .setName(user.getName())
            .setSurname(user.getSurname())
            .setMiddleName(user.getMiddleName())
            .setBirthdate(user.getBirthdate())
            .setExternalId(user.getExternalId());
    }

    @Override
    public void addPost(AddPostDto request, List<MultipartFile> photos) {
        String currentLogin = SecurityContextHelper.getCurrentUserLogin();
        UserEntity user = userRepository.findByLogin(currentLogin);
        PostEntity post = new PostEntity();
        post.setText(request.getText());
        post.setLikes(0L);
        post.setPostingTime(LocalDateTime.now());
        post.setAuthor(user);
        post.setExternalId(UUID.randomUUID());

        post = postRepository.save(post);


        try {
            for(MultipartFile uploadedPhoto : photos) {
                PhotoEntity photo = new PhotoEntity();
                photo.setExternalId(UUID.randomUUID());
                photo.setPost(post);
                String base64 = Base64.getEncoder().encodeToString(uploadedPhoto.getBytes());
                photo.setPhotoBase64(base64.getBytes());
                photoRepository.save(photo);
            }
        } catch(IOException exception) {
            log.error("Exception: ", exception);
            throw new GigaException(ExceptionCode.SERVER_EXCEPTION);
        }
    }
}



























