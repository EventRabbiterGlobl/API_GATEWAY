package com.API_GATEWAY.API_GATEWAY.filter;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;


@Component
public class RouteValidator {


    public static final List<String> openApiEndpoints=List.of(
            "api/v1/auth/register",
            "api/v1/auth/login",
            "/test/yes",
            "/eureka"

    );


    public Predicate<ServerHttpRequest> isSecured=
            request->openApiEndpoints
                    .stream()
                    .noneMatch(uri->request.getURI().getPath().contains(uri));

}
