package com.week4.week4.FIlter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthorizationStudentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("role");
        if (header.equals("STUDENT_ROLE") || header.equals("TEACHER_ROLE")) {
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
    public FilterRegistrationBean<AuthorizationStudentFilter> authorizationStudentFilterBean(){
        FilterRegistrationBean<AuthorizationStudentFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationStudentFilter());
        registrationBean.addUrlPatterns("/api/student/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<AuthorizationTeacherFilter> filterTeacher() {
        FilterRegistrationBean<AuthorizationTeacherFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationTeacherFilter());
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/api/teacher/*");

        return registrationBean;
    }
}

