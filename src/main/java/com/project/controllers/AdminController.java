package com.project.controllers;

import com.project.models.User;
import com.project.services.UserService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/adminProfile")
    public String getAdminPage(ModelMap model, Authentication authentication) {
        Long adminId = userService.getUserId(authentication);
        User admin = userService.getUserById(adminId);
        UserDto adminDto = UserDto.from(admin);
        model.addAttribute("admin", adminDto);
        return "adminProfile";
    }

    @PostMapping("/adminProfile")
    public RedirectView singUpBank() {
        return new RedirectView("/adminProfile/signUpBank");
    }
}
