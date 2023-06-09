package vlsu.ispi.instagram.service.impl;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlsu.ispi.instagram.dto.ProfileDto;
import vlsu.ispi.instagram.dto.RegistrationDto;
import vlsu.ispi.instagram.model.AccessStatus;
import vlsu.ispi.instagram.model.Role;
import vlsu.ispi.instagram.model.RoleEntity;
import vlsu.ispi.instagram.model.UserEntity;
import vlsu.ispi.instagram.repository.RoleRepository;
import vlsu.ispi.instagram.repository.UserRepository;
import vlsu.ispi.instagram.service.GigaService;
import vlsu.ispi.instagram.utils.exception.ExceptionCode;
import vlsu.ispi.instagram.utils.exception.GigaException;

@Service
@RequiredArgsConstructor
@Transactional
public class GigaServiceImpl implements GigaService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
}
