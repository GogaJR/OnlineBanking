package com.project.controllers;

import com.project.forms.BankForm;
import com.project.forms.ButtonForm;
import com.project.forms.UserForm;
import com.project.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUpUser";
    }

    @PostMapping("/signUp")
    public String signUp(ModelMap model, UserForm userForm, ButtonForm buttonForm) {
        if(buttonForm.getButton().equals("ok")) {
            if(!service.isMalePresent(userForm.getMail())) {
                service.signUp(userForm);
                return "redirect:/login";
            }

            model.addAttribute("error", true);
            return "signUpUser";
        }

        return "redirect:/login";
    }

    @GetMapping("adminProfile/signUpBank")
    public String getBankSignUpPage() {
        return "signUpBank";
    }

    @PostMapping("adminProfile/signUpBank")
    public String bankSignUp(ButtonForm buttonForm, BankForm bankForm) {
        if(buttonForm.getButton().equals("ok")) {
            service.signUp(bankForm);
        }

        return "redirect:/adminProfile";
    }
}
