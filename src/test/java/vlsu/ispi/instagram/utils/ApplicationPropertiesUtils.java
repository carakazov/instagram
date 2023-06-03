package vlsu.ispi.instagram.utils;

import lombok.experimental.UtilityClass;
import vlsu.ispi.instagram.config.ApplicationProperties;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }
}
