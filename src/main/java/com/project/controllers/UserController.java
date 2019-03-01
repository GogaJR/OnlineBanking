package com.project.controllers;

import com.project.details.UserDetailsImpl;
import com.project.forms.ButtonForm;
import com.project.models.UserBalanceBank;
import com.project.repositories.UserBalanceBankRepository;
import com.project.services.UserBalanceBankService;
import com.project.services.UserService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserBalanceBankService userBalanceBankService;

    @GetMapping("/userProfile")
    public String getUserProfilePage(ModelMap model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        Optional<UserBalanceBank> userBalanceBank = userBalanceBankService.getUserBalanceBankByUserId(userId);

        if(userBalanceBank.isPresent()) {
            UserDto user = UserDto.from(userDetails.getUser());
            model.addAttribute("user", user);
            return "userProfile";
        }

        UserDto user = UserDto.from(userDetails.getUser());
        model.addAttribute("user", user);
        return "ncuserProfile";
    }

    @PostMapping("/userProfile")
    public RedirectView chooseOption(ButtonForm buttonForm) {
        String optionName = buttonForm.getButton();
        if(optionName.equals("showBankList")) {
            return new RedirectView("/userProfile/bankList");
        } else if(optionName.equals("openAccount")) {
            return new RedirectView("/userProfile/openAccount");
        } else if(optionName.equals("showAccountResponses")) {
            return new RedirectView("/userProfile/accountResponses");
        } else if(optionName.equals("showUserBankList")) {
            return new RedirectView("/userProfile/myBankList");
        } else if(optionName.equals("showBalance")) {
            return new RedirectView("/userProfile/balance");
        } else if(optionName.equals("showBankDepositList")) {
            return new RedirectView("/userProfile/depositList");
        } else if(optionName.equals("putInDeposit")) {
            return new RedirectView("/userProfile/putInDeposit");
        } else if(optionName.equals("getDepositBack")) {
            return new RedirectView("/userProfile/getDepositBack");
        } else if(optionName.equals("makeTransfer")) {
            return new RedirectView("/userProfile/transfer");
        } else if(optionName.equals("showMyTransfers")) {
            return new RedirectView("/userProfile/myTransfers");
        }

        return null;
    }
}
