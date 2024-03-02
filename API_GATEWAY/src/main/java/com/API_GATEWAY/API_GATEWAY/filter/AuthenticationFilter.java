package com.API_GATEWAY.API_GATEWAY.filter;

import com.API_GATEWAY.API_GATEWAY.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {



    @Autowired
    private RouteValidator routeValidator;


    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private RestTemplate restTemplate;


    public AuthenticationFilter() {

        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test((ServerHttpRequest) exchange.getRequest())){

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7);
                }
                try{
                    jwtUtil.validateToken(authHeader);


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
            return chain.filter(exchange);

        });
    }

    public static class Config{

    }
}
