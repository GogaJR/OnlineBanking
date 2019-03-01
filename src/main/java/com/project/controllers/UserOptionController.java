package com.project.controllers;

import com.project.forms.AccountRequestForm;
import com.project.forms.ButtonForm;
import com.project.forms.PutDepositForm;
import com.project.forms.TransferForm;
import com.project.models.*;
import com.project.services.*;
import com.project.transfer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class UserOptionController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserBalanceBankService userBalanceBankService;

    @Autowired
    private AccountRequestService accountRequestService;

    @Autowired
    private BankService bankService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private PutInDepositService putInDepositService;

    @Autowired
    private AccountResponseService accountResponseService;

    @GetMapping("/userProfile/bankList")
    public String getBankListPage(ModelMap model) {
        List<Bank> banks = bankService.getAllBanks();
        model.addAttribute("banks", banks);
        return "banks";
    }

    @GetMapping("/userProfile/openAccount")
    public String getOpenAccountPage(ModelMap model) {
        List<BankDto> bankDtos = new ArrayList<>();
        for(Bank bank : bankService.getAllBanks()) {
            BankDto bankDto = BankDto.from(bank);
            bankDtos.add(bankDto);
        }

        model.addAttribute("banks", bankDtos);
        return "openAccount";
    }

    @PostMapping("/userProfile/openAccount")
    public String sendAccountRequest(ButtonForm buttonForm, AccountRequestForm accountRequestForm, Authentication authentication) {
       if(buttonForm.getButton().equals("sendRequest")) {
           Long userId = userService.getUserId(authentication);
           Long bankId = Long.parseLong(accountRequestForm.getBankName());
           String cardId = accountRequestForm.getCardId();

           Optional<AccountRequest> accountRequest = accountRequestService.getAccountRequestByUserIdAndBankIdAndCardId(userId, bankId, cardId);
           if(!accountRequest.isPresent()) {
               Optional<UserBalanceBank> userBalanceBank = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(userId, bankId);
               if(userBalanceBank.isPresent()) {
                   System.out.println("You already have an account in this bank");
               } else if(userService.isCardIdWrong(cardId)) {
                   System.out.println("Wrong Card Id");
               } else {
                   userService.sendRequest(userId, bankId, cardId);
                   System.out.println("Send Successfully");
               }
           } else {
               System.out.println("Already Created!");
           }
       }

        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile/accountResponses")
    public String showAccountResponses(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<AccountResponse> accountResponses = accountResponseService.getAccountResponsesByUserIdAndRecordState(userId, RecordState.HAS_NOT_BEEN_READ);

        List<AccountResponseDto> accountResponseDtos = new ArrayList<>();
        for (AccountResponse accountResponse : accountResponses) {
            accountResponseDtos.add(AccountResponseDto.from(accountResponse));
        }

        model.addAttribute("accountResponses", accountResponseDtos);
        return "accountResponses";
    }

    @PostMapping("/userProfile/accountResponses")
    public String clearResponses(ButtonForm buttonForm, Authentication authentication) {
        if(buttonForm.getButton().equals("clearRecords")) {
            Long userId = userService.getUserId(authentication);
            List<AccountResponse> accountResponses = accountResponseService.getAccountResponsesByUserIdAndRecordState(userId, RecordState.HAS_NOT_BEEN_READ);
            for (AccountResponse accountResponse : accountResponses) {
                if(accountResponse.getRecordState().equals(RecordState.HAS_NOT_BEEN_READ)) {
                    accountResponse.setRecordState(RecordState.HAS_BEEN_READ);
                    userService.saveResponseState(accountResponse);
                }
            }

            return "accountResponses";
        } else {
            return "redirect:/userProfile";
        }
    }

    @GetMapping("/userProfile/myBankList")
    public String getMyBankListPage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<Bank> userBankList = bankService.getAllBanksByUserId(userId);

        model.addAttribute("banks", userBankList);
        return "banks";
    }

    @GetMapping("/userProfile/balance")
    public String getBalancePage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<UserBalanceBankDto> userBalanceBankDtos = userBalanceBankService.getUserBalanceBankDtosByUserId(userId);

        model.addAttribute("banksAndBalances", userBalanceBankDtos);
        return "balance";
    }

    @PostMapping("/userProfile/**")
    public String getBack() {
        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile/depositList")
    public String chooseBank(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<BankDto> userBanks = bankService.getBankDtosByUserId(userId);

        model.addAttribute("userBankList", userBanks);
        return "chooseBankAndDepositList";
    }

    @PostMapping("/userProfile/depositList")
    public String getDepositList(ModelMap model,ButtonForm buttonForm, HttpServletRequest request, Authentication authentication) {
        if(buttonForm.getButton().equals("show")) {
            Long bankId = Long.parseLong(request.getParameter("bankName"));
            Map<Deposit, List<DepositMonthRate>> depositWithMonthRates = depositService.getBankDepositAndMonthRateList(bankId);

            Set<Deposit> deposits = depositWithMonthRates.keySet();
            List<List<DepositMonthRate>> depositMonthRatesList = new ArrayList<>();
            for(Deposit deposit : deposits) {
                List<DepositMonthRate> depositMonthRates = depositWithMonthRates.get(deposit);
                depositMonthRatesList.add(depositMonthRates);
            }

            model.addAttribute("depositAndMonthRates", depositWithMonthRates);
            return chooseBank(model, authentication);
        } else {
            return "redirect:/userProfile";
        }
    }

    @GetMapping("/userProfile/putInDeposit")
    public String getPutInDepositPage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<BankDto> userBanks = bankService.getBankDtosByUserId(userId);
        List<Deposit> deposits = depositService.getAllDeposits();

        model.addAttribute("userBankList", userBanks);
        model.addAttribute("depositList", deposits);
        return "putInDeposit";
    }

    @PostMapping("/userProfile/putInDeposit")
    public String putInDeposit(ButtonForm buttonForm, PutDepositForm putDepositForm, Authentication authentication) {
        if(buttonForm.getButton().equals("ok")) {
            Long userId = userService.getUserId(authentication);
            String bankName = putDepositForm.getBankName();
            String depositName = putDepositForm.getDepositName();
            if(bankName.equals("") || depositName.equals("")) {
                return "redirect:/userProfile/putInDeposit";
            }

            Long bankId = Long.parseLong(bankName);
            if(depositService.isDepositAlreadyPutIn(userId, bankId)) {
                System.out.println("You Have Already Put Deposit");
                return "redirect:/userProfile/putInDeposit";
            }

            Long depositId = Long.parseLong(depositName);
            int amount = Integer.parseInt(putDepositForm.getDepositAmount());
            if(!userService.isBalanceEnough(userId, bankId, amount)) {
                System.out.println("Your Balance Isn't Enough");
                return "redirect:/userProfile/putInDeposit";
            }

            int months = Integer.parseInt(putDepositForm.getNumberOfMonths());
            userService.putInDeposit(userId, bankId, depositId, amount, months);
        }

        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile/getDepositBack")
    public String getDepositBackPage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<PutInDeposit> userDeposits = putInDepositService.getPutInDepositsByUserId(userId);
        List<PutInDepositDto> userDepositsDtos = new ArrayList<>();
        for (PutInDeposit userDeposit : userDeposits) {
            PutInDepositDto putInDepositDto = PutInDepositDto.forUser(userDeposit);
            userDepositsDtos.add(putInDepositDto);
        }

        model.addAttribute("userDepositList", userDepositsDtos);
        return "userDepositList";
    }

    @PostMapping("/userProfile/getDepositBack")
    public String getDepositBack(ButtonForm buttonForm, HttpServletRequest request, Authentication authentication) {
        if(buttonForm.getButton().equals("getBack")) {
            Long userId = userService.getUserId(authentication);
            if(request.getParameter("bankName") == null) {
                return "redirect:/userProfile/getDepositBack";
            }

            Long bankId = Long.parseLong(request.getParameter("bankName"));
            userService.getDepositBack(userId, bankId);
        }

        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile/transfer")
    public String getTransferPage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<BankDto> userBanks = bankService.getBankDtosByUserId(userId);
        List<Bank> banks = bankService.getAllBanks();
        model.addAttribute("userBankList", userBanks);
        model.addAttribute("bankList", banks);
        return "transfer";
    }

    @PostMapping("/userProfile/transfer")
    public String makeTransfer(ButtonForm buttonForm, TransferForm transferForm, Authentication authentication) {
        if(buttonForm.getButton().equals("makeTransfer")) {
            Long userId = userService.getUserId(authentication);
            Long userBankId = Long.parseLong(transferForm.getUserBankName());
            Long recipientBankId = Long.parseLong(transferForm.getRecipientBankName());
            String recipientName = transferForm.getRecipientName();
            String recipientAccount = transferForm.getAccountTo();
            int amount = Integer.parseInt(transferForm.getAmount());
            if(userService.isBalanceEnough(userId, userBankId, amount)) {
                Role recipientRole = transferService.checkTransfer(userBankId, recipientBankId, recipientName, recipientAccount);
                if(recipientRole != null) {
                    userService.makeTransfer(userId, userBankId, recipientBankId, recipientAccount, amount, recipientRole);
                }
            }
        }

        return "redirect:/userProfile";
    }

    @GetMapping("/userProfile/myTransfers")
    public String getMyTransfersPage(ModelMap model, Authentication authentication) {
        Long userId = userService.getUserId(authentication);
        List<TransferDto> userTransfersTo = transferService.getUserTransferDtosTo(userId, Role.ROLE_USER);
        List<TransferDto> userTransfersFrom = transferService.getUserTransferDtosFrom(userId, Role.ROLE_USER);
        model.addAttribute("userTransfersTo", userTransfersTo);
        model.addAttribute("userTransfersFrom", userTransfersFrom);
        return "userTransfers";
    }
}
