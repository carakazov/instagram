package vlsu.ispi.instagram.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GigaException extends RuntimeException {
    @Getter
    private ExceptionCode code;
}
