package vlsu.ispi.instagram.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "messages")
@Entity(name = "message")
public class MessageEntity extends AbstractIdEntity {
    private String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    private UUID externalId = UUID.randomUUID();

    private LocalDateTime sendingTime;
}
