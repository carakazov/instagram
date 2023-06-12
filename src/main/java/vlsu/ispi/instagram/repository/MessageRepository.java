package vlsu.ispi.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlsu.ispi.instagram.model.MessageEntity;
import vlsu.ispi.instagram.model.UserEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllBySenderAndReceiver(UserEntity sender, UserEntity receiver);
}
