package vlsu.ispi.instagram.dto;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendMessageDto {
    private String text;
    private UUID receiverId;
}
