package com.week4.week4.FIlter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


    @Component()
    @Order(2)
    public class AuthorizationTeacherFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            Filter.super.init(filterConfig);
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String header = request.getHeader("role");
            if (header.equals("TEACHER_ROLE")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(401);
                response.getWriter().write("{\"errorMessage\":\"User unauthorized!\"}");
            }
        }

        @Override
        public void destroy() {
            Filter.super.destroy();
        }

        @Bean
        public FilterRegistrationBean<AuthorizationTeacherFilter> authorizationTeacherFilterBean(){
            FilterRegistrationBean<AuthorizationTeacherFilter> registrationBean
                    = new FilterRegistrationBean<>();

            registrationBean.setFilter(new AuthorizationTeacherFilter());
            registrationBean.addUrlPatterns("/api/teacher/*");
            registrationBean.setOrder(2);

            return registrationBean;
        }
    }

