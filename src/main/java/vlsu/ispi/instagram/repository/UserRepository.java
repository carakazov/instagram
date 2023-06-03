package vlsu.ispi.instagram.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

    UserEntity findByExternalId(UUID externalId);

    void deleteByExternalId(UUID externalId);
}
