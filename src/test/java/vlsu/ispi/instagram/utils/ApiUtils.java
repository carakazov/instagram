package vlsu.ispi.instagram.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import vlsu.ispi.instagram.exception.ExceptionCode;
import vlsu.ispi.instagram.exception.ValidationException;
import vlsu.ispi.instagram.dto.ErrorDto;
import vlsu.ispi.instagram.dto.ValidationErrorDto;

import static vlsu.ispi.instagram.utils.TestDataConstants.*;

@UtilityClass
public class ApiUtils {
    public static ErrorDto errorDto() {
        return new ErrorDto()
                .setCode(EXCEPTION_CODE);
    }

    public static ValidationErrorDto validationErrorDto() {
        return new ValidationErrorDto(Collections.singletonList(errorDto()));
    }

    public static ValidationException validationException() {
        ValidationException validationException = new ValidationException();
        validationException.addCode(ExceptionCode.INTERNAL_ERROR);
        return validationException;
    }
}
