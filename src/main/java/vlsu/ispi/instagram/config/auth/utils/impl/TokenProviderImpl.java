package vlsu.ispi.instagram.config.auth.utils.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vlsu.ispi.instagram.config.ApplicationProperties;
import vlsu.ispi.instagram.config.auth.utils.TokenProvider;
import vlsu.ispi.instagram.model.UserEntity;

@Component
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    private static final String ROLE = "role";

    private final ApplicationProperties applicationProperties;

    @Override
    public String provide(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLE, user.getRole().getRole().name());
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getLogin())
            .setExpiration(new Date(new Date().getTime() + applicationProperties.getJwt().getExpirationTime()))
            .signWith(SignatureAlgorithm.HS256, applicationProperties.getJwt().getSecret())
            .compact();
    }
}
