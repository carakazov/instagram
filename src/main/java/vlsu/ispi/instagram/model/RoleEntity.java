package vlsu.ispi.instagram.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "roles")
@Entity(name = "role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AbstractIdEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
