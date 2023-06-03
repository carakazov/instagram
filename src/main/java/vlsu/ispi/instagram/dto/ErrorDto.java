package vlsu.ispi.instagram.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Данные об ошибке")
public class ErrorDto {
    @ApiModelProperty(value = "Код ошибки", required = true)
    private String code;
}
