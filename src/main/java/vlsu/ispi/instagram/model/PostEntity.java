package vlsu.ispi.instagram.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "posts")
@Entity(name = "post")
public class PostEntity extends AbstractIdEntity {
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    private UUID externalId;

    private LocalDateTime postingTime;

    private Long likes;

    @ManyToMany
    @JoinTable(name = "post_user_likes",
               joinColumns = @JoinColumn(name = "post_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> likedUsers;
}
