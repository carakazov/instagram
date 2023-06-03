package vlsu.ispi.instagram.config.auth.utils.impl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vlsu.ispi.instagram.config.ApplicationProperties;
import vlsu.ispi.instagram.config.auth.utils.TokenValidator;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenValidatorImpl implements TokenValidator {
    private final ApplicationProperties applicationProperties;


    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(applicationProperties.getJwt().getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt");
        } catch (SignatureException sEx) {
            log.error("Invalid signature");
        } catch (Exception e) {
            log.error("invalid token");
        }
        return false;
    }
}
