package com.example.project.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

import com.example.project.entity.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    @Autowired
    private com.example.project.service.UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        java.util.Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        //org.springframework.security.web.savedrequest.SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        HttpSession session = request.getSession();
        String email = authentication.getName();
        user user = userService.findUserByEmail(email);
        session.setAttribute("user", user);
        if (roles.contains("ROLE_ADMIN") ) {
            getRedirectStrategy().sendRedirect(request, response, "admin/dashboard");
        }else if(roles.contains("ROLE_DOCTOR")){
            getRedirectStrategy().sendRedirect(request, response, "/home");
        }else if(roles.contains("ROLE_MANAGER")){
            getRedirectStrategy().sendRedirect(request, response, "/home");
        }
        else {
            getRedirectStrategy().sendRedirect(request, response, "/home");
            System.out.println("home");
        }
    }

}
