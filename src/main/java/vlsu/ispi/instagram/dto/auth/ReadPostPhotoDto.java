package vlsu.ispi.instagram.dto.auth;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReadPostPhotoDto {
    private String photoBase64;
    private UUID externalId;
}
