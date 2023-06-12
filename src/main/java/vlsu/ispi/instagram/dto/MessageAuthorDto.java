package vlsu.ispi.instagram.dto;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.propertyeditors.UUIDEditor;

@Data
@Accessors(chain = true)
public class MessageAuthorDto {
    private String name;
    private String surname;
    private UUID externalId;
}
