package com.API_GATEWAY.API_GATEWAY.filter;


import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;


@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/auth/otp",
            "/test/yes",
            "/eureka"
    );

    public boolean isSecured(URI requestUri) {
        String requestPath = requestUri.getPath();
        return openApiEndpoints.stream().noneMatch(requestPath::equals);
    }
}
