package com.ilearn.dubbo.member.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ilearn.dubbo.appframework.web.XssHttpServletRequestWraper;

/**
 * Created by George on 2018/2/2 0002.
 */
public class AccessFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest rawRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        XssHttpServletRequestWraper request = new XssHttpServletRequestWraper(rawRequest);
        filterChain.doFilter(request, response);
    }
}
