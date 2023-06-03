package vlsu.ispi.instagram.config.auth.utils;

public interface TokenDecoder {
    String getLoginFromToken(String token);
}
