package sk.fmfi.listng.rest.security.utils;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityUserIdResolverImpl implements SecurityUserIdResolver {

    @Override
    public String extractUserId(HttpServletRequest request, HttpServletResponse response) {
        return request.getHeader(USER_ID);
    }

}
