package vlsu.ispi.instagram.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;
import vlsu.ispi.instagram.dto.auth.ReadPostPhotoDto;

@Data
@Accessors(chain = true)
public class PostSimpleDto {
    private UUID externalId;
    private ReadPostPhotoDto cover;

    private LocalDateTime postingTime;
}
