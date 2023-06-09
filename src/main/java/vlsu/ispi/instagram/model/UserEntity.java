package vlsu.ispi.instagram.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.userdetails.User;

@Data
@Table(name = "users")
@Entity(name = "user")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends AbstractIdEntity {
    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @NotNull
    private UUID externalId = UUID.randomUUID();

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String middleName;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private AccessStatus accessStatus;

    @ManyToMany
    @JoinTable(name = "blacklist",
               joinColumns = @JoinColumn(name = "blocked_by_id"),
               inverseJoinColumns = @JoinColumn(name = "blocked_user_id")
    )
    private List<UserEntity> blacklistedUsers;
}
