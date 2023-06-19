package vlsu.ispi.instagram.dto;

import java.util.UUID;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.experimental.Accessors;
import vlsu.ispi.instagram.model.AccessStatus;

@Data
@Accessors(chain = true)
public class UserDto {
    private String name;
    private String surname;
    private String middleName;
    private UUID externalId;
    private String access;
}
