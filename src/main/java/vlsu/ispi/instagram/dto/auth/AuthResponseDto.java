package vlsu.ispi.instagram.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(description = "Ответ об успешной авторизации")
@AllArgsConstructor
public class AuthResponseDto {
    @ApiModelProperty(value = "Токен", required = true)
    private String token;
}
