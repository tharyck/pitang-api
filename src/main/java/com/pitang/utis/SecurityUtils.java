package com.pitang.utis;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityUtils {

    public static final String TOKEN_HEADER = "authorization";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "*";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "OPTIONS, GET, POST, DELETE, PUT, PATCH";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "authorization, content-type, refresh-token";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "authorization, content-type, refresh-token";

    public HttpServletResponse fillAccessControlHeader(HttpServletResponse response) {

        if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)) {
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN);
        }

        if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS)) {
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS);
        }

        if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS)) {
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS);
        }

        if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS)) {
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ACCESS_CONTROL_EXPOSE_HEADERS);
        }

        return response;
    }
}