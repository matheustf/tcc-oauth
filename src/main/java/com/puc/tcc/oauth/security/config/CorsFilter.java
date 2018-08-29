package com.puc.tcc.oauth.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component
public class CorsFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-access-token, Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "x-access-token, Content-Type");

        final HttpServletRequest request = (HttpServletRequest) req;

        if (request.getMethod().equals("OPTIONS")) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}