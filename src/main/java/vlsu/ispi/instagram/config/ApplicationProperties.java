package vlsu.ispi.instagram.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application")
@Component
@Accessors(chain = true)
public class ApplicationProperties {

    private Jwt jwt;

    @Data
    public static class Jwt {
        private String secret;
        private Long expirationTime;
    }
}
