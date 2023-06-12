package vlsu.ispi.instagram.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;
import vlsu.ispi.instagram.dto.auth.ReadPostPhotoDto;

@Data
@Accessors(chain = true)
public class ProfileDto {
    private String name;
    private String surname;
    private String middleName;
    private LocalDate birthdate;
    private UUID externalId;
    private List<PostSimpleDto> simplePosts;
}
