package com.example.project.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.project.entity.user;

public class CustomUser implements UserDetails{
   
    private user u;
    
    
     public CustomUser(user user) {
            this.u = user;
    }


    @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
         SimpleGrantedAuthority authority = new SimpleGrantedAuthority(u.getRolename());
         return java.util.Arrays.asList(authority);
     }


    @Override
    public String getPassword() {
        return u.getPassword();
    }
    
    @Override
    public String getUsername() {
        return u.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
