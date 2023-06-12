package vlsu.ispi.instagram.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = false)
public class DialogDto {
    private List<ReadMessageDto> messages;
}
