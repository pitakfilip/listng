package sk.fmfi.listng.rest.security.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rucka
 */
@Component
public interface SecurityUserIdResolver {

    String USER_ID = "listUserId";

    String extractUserId(HttpServletRequest request, HttpServletResponse response);
}
