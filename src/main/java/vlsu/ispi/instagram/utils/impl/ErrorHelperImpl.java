package vlsu.ispi.instagram.utils.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import vlsu.ispi.instagram.dto.ErrorDto;
import vlsu.ispi.instagram.utils.ErrorHelper;

@Component
public class ErrorHelperImpl implements ErrorHelper {
    @Override
    public ErrorDto from(Exception exception) {
        return null;
    }

    @Override
    public ErrorDto from(MethodArgumentNotValidException exception) {
        return null;
    }
}
