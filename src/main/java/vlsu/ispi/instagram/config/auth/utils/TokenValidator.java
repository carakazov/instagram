package vlsu.ispi.instagram.config.auth.utils;

public interface TokenValidator {
    boolean validate(String token);
}
