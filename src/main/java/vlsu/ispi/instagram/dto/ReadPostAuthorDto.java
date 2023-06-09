package vlsu.ispi.instagram.dto;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReadPostAuthorDto {
    private String name;
    private UUID externalId;
}
