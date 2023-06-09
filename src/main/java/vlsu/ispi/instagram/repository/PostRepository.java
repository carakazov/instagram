package vlsu.ispi.instagram.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    PostEntity findByExternalId(UUID externalId);
}
