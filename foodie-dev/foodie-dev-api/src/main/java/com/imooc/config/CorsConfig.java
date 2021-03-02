package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. add cors configuration
        CorsConfiguration config = new CorsConfiguration();
        // 前端请求的调用端
        config.addAllowedOrigin("http://localhost:8080");

        // set cookie info, same as front end, with Credentials
        config.setAllowCredentials(true);

        //post, get, etc
        config.addAllowedMethod("*");

        config.addAllowedHeader("*");

        // 2. add mapping for url
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3. return new corsSource
        return new CorsFilter(corsSource);



    }

}

