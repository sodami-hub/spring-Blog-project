package com.estsoft.springprojectblogexam.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FirstFilter.doFilter() - request");

        // requestURI 로그 남기기
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("requestURI : " + request.getRequestURI() + " request.Method : " + request.getMethod());


        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("FirstFilter.doFilter() - response");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter.destroy()");
    }


}