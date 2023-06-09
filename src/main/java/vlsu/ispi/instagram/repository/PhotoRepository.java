package vlsu.ispi.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.PhotoEntity;
import vlsu.ispi.instagram.model.PostEntity;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    List<PhotoEntity> findAllByPost(PostEntity post);
}
