package com.project.controllers;

import com.project.details.UserDetailsImpl;
import com.project.forms.ButtonForm;
import com.project.models.PutInDeposit;
import com.project.models.RecordState;
import com.project.services.BankService;
import com.project.services.PutInDepositService;
import com.project.transfer.BankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private PutInDepositService putInDepositService;

    @GetMapping("/bankProfile")
    public String getBankProfilePage(ModelMap model, Authentication authentication) {
        UserDetailsImpl bankDetails = (UserDetailsImpl) authentication.getPrincipal();
        BankDto bank = BankDto.from(bankDetails.getBank());

        Long bankId = bankService.getBankId(authentication);
        List<PutInDeposit> putInDeposits = putInDepositService.getPutInDepositsByBankIdAndRecordState(bankId, RecordState.HAS_NOT_BEEN_READ);
        for (PutInDeposit putInDeposit : putInDeposits) {
            LocalDate depositIncreasingDay = putInDeposit.getIncreasingDay().toLocalDate();
            if(depositIncreasingDay.isEqual(LocalDate.now()) || depositIncreasingDay.isBefore(LocalDate.now())) {
                bankService.increaseDeposit(putInDeposit);
            }
        }

        model.addAttribute("bank", bank);
        return "bankProfile";
    }

    @PostMapping("/bankProfile")
    public RedirectView chooseOption(ButtonForm buttonForm) {
        String optionName = buttonForm.getButton();
        if(optionName.equals("showAllUsers")) {
            return new RedirectView("/bankProfile/usersList");
        } else if(optionName.equals("showAccountRequests")) {
            return new RedirectView("/bankProfile/accountRequests");
        } else if(optionName.equals("addDeposit")) {
            return new RedirectView("/bankProfile/addDeposit");
        } else if(optionName.equals("showPutInDeposits")) {
            return new RedirectView("/bankProfile/putInDeposits");
        } else if(optionName.equals("showTransfers")) {
            return new RedirectView("/bankProfile/transfers");
        }

        return null;
    }
}
