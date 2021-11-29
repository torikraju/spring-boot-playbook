package com.self.learning.playbook.constant;

import java.util.concurrent.TimeUnit;

public class SecurityConstant {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public static final String[] SWAGGER = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
    public static final String[] RESOURCE = {
            "/",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };

    public static final String[] OPEN_URLS = {
            "/authentication/**",
            "/css/**"
    };

    public static final String AUTH_TOKEN_SECRET="123456";
    public static final long EXPIRATION_TIME_TOKEN = TimeUnit.HOURS.toMillis(176L);
}