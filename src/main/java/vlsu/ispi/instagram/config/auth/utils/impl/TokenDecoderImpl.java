package vlsu.ispi.instagram.config.auth.utils.impl;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlsu.ispi.instagram.config.ApplicationProperties;
import vlsu.ispi.instagram.config.auth.utils.TokenDecoder;

@Component
@RequiredArgsConstructor
public class TokenDecoderImpl implements TokenDecoder {
    private final ApplicationProperties applicationProperties;

    @Override
    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(applicationProperties.getJwt().getSecret()).parseClaimsJws(token).getBody().getSubject();
    }
}
