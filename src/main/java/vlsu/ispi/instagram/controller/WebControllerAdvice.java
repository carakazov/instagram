package vlsu.ispi.instagram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import vlsu.ispi.instagram.dto.ErrorDto;
import vlsu.ispi.instagram.dto.ValidationErrorDto;
import vlsu.ispi.instagram.utils.ErrorHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vlsu.ispi.instagram.utils.exception.AuthException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class WebControllerAdvice {
    private final ErrorHelper errorHelper;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleCommonException(Exception exception) {
        ErrorDto errorDto = errorHelper.from(exception);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException() {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
