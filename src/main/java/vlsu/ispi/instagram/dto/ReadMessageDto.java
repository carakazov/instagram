package vlsu.ispi.instagram.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReadMessageDto {
    private MessageAuthorDto author;
    private String text;
    private LocalDateTime sendingTime;
    private UUID externalId;
}
