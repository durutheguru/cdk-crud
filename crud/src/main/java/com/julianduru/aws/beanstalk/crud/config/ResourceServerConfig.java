package com.julianduru.aws.beanstalk.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * created by julian on 25/04/2022
 */
//@Configuration
//@EnableWebSecurity
public class ResourceServerConfig {


//    @Value("${code.config.oauth2.resource-server.serverId}")
//    private String resourceServerId;
//
//
//    @Value("${code.config.oauth2.resource-server.jwk-set-uri}")
//    private String jwkSetUri;


//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                c -> {
//                    c.requestMatchers("/health")
//                        .permitAll();
//                }
//            );
//
//        return http.build();
//    }



//    @Bean
//    public CorsConfigurationSource resourceServerCorsConfiguration(
//        @Value("${code.config.web.cors.allowed-origins}") String allowedOrigins
//    ) {
//        var corsConfig = new CorsConfiguration();
//        corsConfig.applyPermitDefaultValues();
//        corsConfig.setAllowCredentials(true);
//        corsConfig.addAllowedMethod("GET");
//        corsConfig.addAllowedMethod("PATCH");
//        corsConfig.addAllowedMethod("POST");
//        corsConfig.addAllowedMethod("OPTIONS");
//        corsConfig.setAllowedOrigins(List.of(allowedOrigins.split("\\s*,\\s*")));
//
//        var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return source;
//    }
//
//
//    @Bean
//    public CorsWebFilter corsWebFilter(CorsConfigurationSource corsConfiguration) {
//        return new CorsWebFilter(corsConfiguration);
//    }


}



