package vlsu.ispi.instagram.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityContextHelper {
    public static String getCurrentUserLogin() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static String getCurrentUserRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();
    }
}
