package vlsu.ispi.instagram.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;
import vlsu.ispi.instagram.dto.auth.ReadPostPhotoDto;

@Data
@Accessors(chain = true)
public class ReadPostDto {
    private String caption;
    private ReadPostAuthorDto author;
    private LocalDateTime postingTime;
    private Long likes;
    private UUID externalId;
    private List<ReadPostPhotoDto> photosBase64;
}
