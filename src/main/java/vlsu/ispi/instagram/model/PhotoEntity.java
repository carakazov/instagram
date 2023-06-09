package vlsu.ispi.instagram.model;

import java.util.UUID;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "photos")
@Entity(name = "photo")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PhotoEntity extends AbstractIdEntity {
    @Column(name = "photo_base_64")
    private byte[] photoBase64;

    private UUID externalId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
