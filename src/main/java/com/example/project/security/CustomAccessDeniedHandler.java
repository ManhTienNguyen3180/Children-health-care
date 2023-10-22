package com.example.project.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAccessDeniedHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
            throws jakarta.servlet.ServletException, IOException {
          
        if (request.getRequestURI().equals("/reservationcontact") && request.getParameter("date") == null) {
            request.getRequestDispatcher("/denied").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    

}
