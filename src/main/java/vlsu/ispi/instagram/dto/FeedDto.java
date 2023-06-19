package vlsu.ispi.instagram.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeedDto {
    private List<PostSimpleDto> feed;
}
