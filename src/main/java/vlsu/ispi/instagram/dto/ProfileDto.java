package vlsu.ispi.instagram.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileDto {
    private String name;
    private String surname;
    private String middleName;
    private LocalDate birthdate;
    private UUID externalId;
}
