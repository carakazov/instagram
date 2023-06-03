package vlsu.ispi.instagram.config.auth;

import java.util.HashSet;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vlsu.ispi.instagram.model.UserEntity;
import vlsu.ispi.instagram.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByLogin(username);
        if(Objects.isNull(userEntity)) {
            log.error("User with login {0} not found", username);
            throw new UsernameNotFoundException("User not found");
        }

        HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole().getRole().name()));

        return new User(userEntity.getLogin(), userEntity.getPassword(), roles);
    }
}
