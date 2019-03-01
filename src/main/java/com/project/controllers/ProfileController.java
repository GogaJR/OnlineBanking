package com.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProfileController {
    @GetMapping("/")
    public RedirectView getProfilePage(Authentication authentication) {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return new RedirectView("/userProfile");
        } else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new RedirectView("/adminProfile");
        } else {
            return new RedirectView("/bankProfile");
        }
    }
}
