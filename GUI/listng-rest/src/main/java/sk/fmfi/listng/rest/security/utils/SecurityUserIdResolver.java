package sk.fmfi.listng.rest.security.utils;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public interface SecurityUserIdResolver {

    String USER_ID = "listUserId";

    String extractUserId(HttpServletRequest request, HttpServletResponse response);
}
