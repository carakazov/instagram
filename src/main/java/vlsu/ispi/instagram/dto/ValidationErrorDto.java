package vlsu.ispi.instagram.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ApiModel(description = "Валидационная ошибка при запросе")
public class ValidationErrorDto {
    @ApiModelProperty(value = "Список ошибок", required = true)
    private List<ErrorDto> validationErrors;
}
