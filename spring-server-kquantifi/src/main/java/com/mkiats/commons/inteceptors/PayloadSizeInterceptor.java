package com.mkiats.commons.inteceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PayloadSizeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        int requestSize = request.getContentLength();
        System.out.println("Request Size: " + requestSize + " bytes");

        return true; // Proceed with the next interceptor or the handler method
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        int responseSize = response.getBufferSize();
        System.out.println("Response Size: " + responseSize + " bytes");
    }
}
