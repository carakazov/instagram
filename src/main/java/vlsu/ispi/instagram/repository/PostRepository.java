package vlsu.ispi.instagram.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.PostEntity;
import vlsu.ispi.instagram.model.UserEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByExternalId(UUID externalId);

    List<PostEntity> findAllByAuthor(UserEntity author);
}
