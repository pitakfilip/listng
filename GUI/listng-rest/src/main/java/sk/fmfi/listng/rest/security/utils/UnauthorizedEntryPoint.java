package sk.fmfi.listng.rest.security.utils;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized access");
    }
}
