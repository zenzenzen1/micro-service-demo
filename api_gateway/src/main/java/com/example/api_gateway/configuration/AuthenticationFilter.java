package com.example.api_gateway.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.example.api_gateway.dto.response.ApiResponse;
import com.example.api_gateway.service.IdentityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {
    private final IdentityService identityService;
    private final ObjectMapper objectMapper;

    private String[] publicEndpoints = {
            "/identity/auth/token",
            "/identity/auth/introspect",    
            "/identity/users/registration",
    };
    
    @Value("${app.api-prefix}")
    private String apiPrefix;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthenticationFilter ... path: {}", exchange.getRequest().getURI().getPath());
        
        if(isPublicEndpoint(exchange.getRequest())) {
            log.info("Public endpoint");
            return chain.filter(exchange);
        }
        
        // Get token from authorization header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        System.out.println("request: " + exchange.getRequest());
        if (CollectionUtils.isEmpty(authHeader)) {
            return unauthenticated(exchange.getResponse());
        }

        // verify token
        if (authHeader != null) {
            String token = authHeader.get(0).replace("Bearer ", "");
            log.info("Token {}", token);
            return identityService.introspect(token).flatMap(result -> {
                if (result.getResult().isValid()) {
                    return chain.filter(exchange);
                }
                else{
                    return unauthenticated(exchange.getResponse());
                }
            }).onErrorResume(t -> unauthenticated(exchange.getResponse()));
        }
        // identity service

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unauthenticated(ServerHttpResponse response) {
        ApiResponse<?> apiResponse = ApiResponse.builder().code(1100).message("Unauthenticated").build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono
                .just(response.bufferFactory().wrap(body == null ? "unauthenticated".getBytes() : body.getBytes())));
    }
    
    private boolean isPublicEndpoint(ServerHttpRequest request){
        String path = request.getURI().getPath();
        for(String publicEndpoint : publicEndpoints){
            if(path.matches(apiPrefix + publicEndpoint)){
                return true;
            }
        }
        return false;
    
    }
}
