package vlsu.ispi.instagram.dto.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Запрос на авторизацию")
public class AuthRequestDto {
    @NotNull
    @ApiModelProperty(value = "Логин", required = true)
    private String login;

    @NotNull
    @ApiModelProperty(value = "Пароль", required = true)
    private String password;
}
