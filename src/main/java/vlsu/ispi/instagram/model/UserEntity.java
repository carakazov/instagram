package vlsu.ispi.instagram.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    private String email;

    private String phone;
}
