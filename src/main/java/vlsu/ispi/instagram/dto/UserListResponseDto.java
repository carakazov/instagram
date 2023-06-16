package vlsu.ispi.instagram.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserListResponseDto {
    private List<UserDto> users;
}
