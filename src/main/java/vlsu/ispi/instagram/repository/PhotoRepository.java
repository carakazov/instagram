package vlsu.ispi.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.PhotoEntity;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
}
