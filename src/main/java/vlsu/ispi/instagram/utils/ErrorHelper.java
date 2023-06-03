package vlsu.ispi.instagram.utils;

import vlsu.ispi.instagram.dto.ErrorDto;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorHelper {
    ErrorDto from(Exception exception);
    ErrorDto from(MethodArgumentNotValidException exception);
}
