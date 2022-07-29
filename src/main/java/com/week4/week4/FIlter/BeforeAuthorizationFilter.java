package com.week4.week4.FIlter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@Order(0)
public class BeforeAuthorizationFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAuthorizationFilter.class);


    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("Pre");

        Instant instantStart = Instant.now();

        // Continue chains of filter
        filterChain.doFilter(servletRequest, servletResponse);

        LOGGER.info("TIME{}: {} ms", ((HttpServletRequest) servletRequest).getRequestURI(), Duration.between(instantStart, Instant.now()).toMillis());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}