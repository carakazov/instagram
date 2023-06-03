package vlsu.ispi.instagram.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private byte[] photoBase64;

    private UUID externalId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
