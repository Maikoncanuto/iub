package com.github.maikoncanuto.iubspringboot.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("[handler] - metodo: {}, uri: {}, ip: {},  remoteAddr: {}, host: {}, port: {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getHeader("X-FORWARDED-FOR"),
                request.getRemoteAddr(),
                request.getRemoteHost(),
                request.getRemotePort());

        return true;
    }
}
