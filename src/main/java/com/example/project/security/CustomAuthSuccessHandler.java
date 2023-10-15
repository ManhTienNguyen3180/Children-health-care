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
        org.springframework.security.web.savedrequest.SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        HttpSession session = request.getSession();
        String email = authentication.getName();
        user user = userService.findUserByEmail(email);
        session.setAttribute("user", user);
        if (roles.contains("ROLE_ADMIN")) {
            getRedirectStrategy().sendRedirect(request, response, "/bloglistmanager");

        }
        else if(savedRequest != null && savedRequest.getRedirectUrl().equalsIgnoreCase("http://localhost:8080/abc?continue")){
            String continueUrl = savedRequest.getRedirectUrl();
            System.out.println(continueUrl+"/servicedetail");
            int idservice = (Integer) session.getAttribute("serviceIds");
            session.removeAttribute("serviceIds");
            getRedirectStrategy().sendRedirect(request, response, "/service-detail/" + idservice);

        }else if (savedRequest != null && savedRequest.getRedirectUrl().equalsIgnoreCase("http://localhost:8080/ad?continue")) {
            String continueUrl = savedRequest.getRedirectUrl();
            System.out.println(continueUrl);
            getRedirectStrategy().sendRedirect(request, response, "/service");

        }
        else if (savedRequest != null && savedRequest.getRedirectUrl().equalsIgnoreCase("http://localhost:8080/blog-detail/images/logo-dark.png?continue")) {
            String continueUrl = savedRequest.getRedirectUrl();
            System.out.println(continueUrl + "/blogdetail");
            int id = (Integer) session.getAttribute("blogId");
            session.removeAttribute("blogId");
            getRedirectStrategy().sendRedirect(request, response, "/blog-detail/" + id);

        } else if(savedRequest != null && savedRequest.getRedirectUrl().equalsIgnoreCase("http://localhost:8080/assets/images/layouts/dark-dash-rtl.png?continue")){
            String continueUrl = savedRequest.getRedirectUrl();
            System.out.println(continueUrl+"/doctor-detail");
            int iddoctor = (Integer) session.getAttribute("doctorIds");
            session.removeAttribute("doctorIds");
            getRedirectStrategy().sendRedirect(request, response, "/doctor-detail/"+iddoctor);

        }else if(savedRequest != null ){
            String continueUrl = savedRequest.getRedirectUrl();
            System.out.println(continueUrl+"/blog");
            getRedirectStrategy().sendRedirect(request, response, continueUrl);
        }
        else {
            getRedirectStrategy().sendRedirect(request, response, "/home");
            System.out.println("home");
        }
    }

}
