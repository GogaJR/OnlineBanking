package com.project.controllers;

import com.project.forms.AccountResponseForm;
import com.project.forms.ButtonForm;
import com.project.models.*;
import com.project.services.*;
import com.project.transfer.AccountRequestDto;
import com.project.transfer.PutInDepositDto;
import com.project.transfer.UserBalanceBankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BankOptionController {

    @Autowired
    private BankService bankService;

    @Autowired
    private PutInDepositService putInDepositService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserBalanceBankService userBalanceBankService;

    @Autowired
    private AccountRequestService accountRequestService;

    @GetMapping("/bankProfile/usersList")
    public String getUserListPage(ModelMap model, Authentication authentication) {
        Long bankId = bankService.getBankId(authentication);
        List<UserBalanceBank> userBalanceBanks = userBalanceBankService.getUserBalanceBanksByBankId(bankId);

        List<UserBalanceBankDto> userBalanceBankDtos = new ArrayList<>();
        for(UserBalanceBank userBalanceBank : userBalanceBanks) {
            userBalanceBankDtos.add(UserBalanceBankDto.from(userBalanceBank));
        }

        model.addAttribute("users", userBalanceBankDtos);
        return "users";
    }

    @GetMapping("/bankProfile/accountRequests")
    public String getAccountRequests(ModelMap model, Authentication authentication) {
        Long bankId = bankService.getBankId(authentication);
        List<AccountRequest> accountRequests = accountRequestService.getAccountRequestsByBankIdAndResponse(bankId, Response.WAITING);
        List<AccountRequestDto> requestDtos = new ArrayList<>();

        if(!accountRequests.isEmpty()) {
            for(AccountRequest accountRequest : accountRequests) {
                AccountRequestDto requestDto = AccountRequestDto.from(accountRequest);
                requestDtos.add(requestDto);
            }
        }

        model.addAttribute("requests", requestDtos);
        return "accountRequests";
    }

    @PostMapping("/bankProfile/accountRequests")
    public String sendAccountResponse(ButtonForm buttonForm, AccountResponseForm accountResponseForm,
                                      Authentication authentication) {
       if(buttonForm.getButton().equals("send")) {
           if(accountResponseForm.getUserIdAndCardId() != null) {
               String resp = accountResponseForm.getResponse();
               Response response;
               if(resp.equals("Accept")) {
                   response = Response.ACCEPTED;
               } else {
                   response = Response.DENIED;
               }

               long userBalance = Long.parseLong(accountResponseForm.getBalance());
               Long bankId = bankService.getBankId(authentication);
               String[] userIdAndBankId = accountResponseForm.getUserIdAndCardId().split(" ");
               Long userId = Long.parseLong(userIdAndBankId[0]);
               String cardId = userIdAndBankId[1];
               bankService.sendResponse(userId, bankId, cardId, response, userBalance);
           }

           return "redirect:/bankProfile/accountRequests";
       }

       return "redirect:/bankProfile";
    }

    @GetMapping("/bankProfile/addDeposit")
    public String getAddDepositPage() {
        return "addDeposit";
    }

    @PostMapping("/bankProfile/addDeposit")
    public String addDeposit(HttpServletRequest request, Authentication authentication) {
        if(request.getParameter("button").equals("addDeposit")) {
            Long bankId = bankService.getBankId(authentication);
            String depositName = request.getParameter("depositName");
            Integer minimumAmount = Integer.parseInt(request.getParameter("minimumAmount"));
            List<Integer> monthLowerBounds = new ArrayList<>();
            List<Integer> monthUpperBounds = new ArrayList<>();
            List<Double> interestRates = new ArrayList<>();

            int index = 1;
            while (request.getParameter("interestRate" + index) != "") {
                Integer monthLowerBound = Integer.parseInt(request.getParameter("monthLowerBound" + index));
                Integer monthUpperBound = Integer.parseInt(request.getParameter("monthUpperBound" + index));
                Double interestRate = Double.parseDouble(request.getParameter("interestRate" + index));

                monthLowerBounds.add(monthLowerBound);
                monthUpperBounds.add(monthUpperBound);
                interestRates.add(interestRate);
                index++;
            }

            bankService.addDeposit(depositName, minimumAmount, bankId, monthLowerBounds, monthUpperBounds, interestRates);
        }

        return "redirect:/bankProfile";
    }

    @GetMapping("/bankProfile/putInDeposits")
    public String getPutInDeposits(ModelMap model, Authentication authentication) {
        Long bankId = bankService.getBankId(authentication);
        List<PutInDeposit> putInDeposits = putInDepositService.getPutInDepositsByBankIdAndRecordState(bankId, RecordState.HAS_NOT_BEEN_READ);
        List<PutInDepositDto> activeDepositDtos = new ArrayList<>();
        List<PutInDepositDto> finishedDepositDtos = new ArrayList<>();
        List<PutInDepositDto> getBackDepositDtos = new ArrayList<>();

        for(PutInDeposit putInDeposit : putInDeposits) {
            PutInDepositDto putInDepositDto = PutInDepositDto.forBank(putInDeposit);
            if(putInDeposit.getDepositState().equals(DepositState.ACTIVE)) {
                activeDepositDtos.add(putInDepositDto);
            } else if(putInDeposit.getDepositState().equals(DepositState.HAS_BEEN_GET_BACK)) {
                getBackDepositDtos.add(putInDepositDto);
            } else {
                finishedDepositDtos.add(putInDepositDto);
            }
        }

        model.addAttribute("activeDeposits", activeDepositDtos);
        model.addAttribute("getBackDeposits", getBackDepositDtos);
        model.addAttribute("finishedDeposits", finishedDepositDtos);
        return "bankPutInDeposits";
    }

    @PostMapping("/bankProfile/putInDeposits")
    public String clearRecords(ModelMap model, ButtonForm buttonForm, Authentication authentication) {
        if(buttonForm.getButton().equals("clearRecords")) {
            Long bankId = bankService.getBankId(authentication);
            List<PutInDeposit> putInDeposits = putInDepositService.getPutInDepositsByBankIdAndRecordState(bankId, RecordState.HAS_NOT_BEEN_READ);
            List<PutInDepositDto> activeDepositDtos = new ArrayList<>();
            for (PutInDeposit putInDeposit : putInDeposits) {
                if(!putInDeposit.getDepositState().equals(DepositState.ACTIVE)) {
                    putInDeposit.setRecordState(RecordState.HAS_BEEN_READ);
                    bankService.saveDepositRecordState(putInDeposit);
                } else {
                    PutInDepositDto putInDepositDto = PutInDepositDto.forBank(putInDeposit);
                    activeDepositDtos.add(putInDepositDto);
                }
            }

            model.addAttribute("activeDeposits", activeDepositDtos);
            return "bankPutInDeposits";
        } else {
            return "redirect:/bankProfile";
        }
    }

    @GetMapping("/bankProfile/transfers")
    public String getTransfersPage(ModelMap model, Authentication authentication) {
        Long bankId = bankService.getBankId(authentication);
        List<Transfer> transfers = transferService.getTransfersByBankId(bankId);
        List<Transfer> inTransfers = new ArrayList<>();
        List<Transfer> outTransfers = new ArrayList<>();
        for (Transfer transfer : transfers) {
            if(transfer.getSenderBank().getId() == bankId && transfer.getRecipientBank().getId() == bankId) {
                inTransfers.add(transfer);
            } else {
                outTransfers.add(transfer);
            }
        }

        model.addAttribute("inTransfers", inTransfers);
        model.addAttribute("outTransfers", outTransfers);
        return "bankTransfers";
    }

    @PostMapping("/bankProfile/**")
    public String getBack() {
        return "redirect:/bankProfile";
    }
}
