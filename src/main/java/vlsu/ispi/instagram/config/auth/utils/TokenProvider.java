package vlsu.ispi.instagram.config.auth.utils;

import vlsu.ispi.instagram.model.UserEntity;

public interface TokenProvider {
    String provide(UserEntity user);
}
