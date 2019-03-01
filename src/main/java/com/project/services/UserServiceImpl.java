package com.project.services;

import com.project.details.UserDetailsImpl;
import com.project.joins.UserBankId;
import com.project.models.*;
import com.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBalanceBankService userBalanceBankService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountRequestService accountRequestService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private PutInDepositService putInDepositService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountResponseService accountResponseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepositMonthRateRepository depositMonthRateRepository;

    @Override
    public void sendRequest(Long userId, Long bankId, String cardId) {
        Bank bank = bankService.getBankById(bankId);
        User user = userRepository.findOne(userId);
        AccountRequest request = AccountRequest.builder()
                                .id(new UserBankId(userId, bankId))
                                .bank(bank)
                                .user(user)
                                .cardId(cardId)
                                .response(Response.WAITING)
                                .build();
        accountRequestService.saveAccountRequest(request);
    }

    @Override
    @Transactional
    public void putInDeposit(Long userId, Long bankId, Long depositId, int amount, int months) {
        List<DepositMonthRate> depositMonthRates = depositMonthRateRepository.findAllByDepositId(depositId);
        Deposit deposit = depositService.getDepositById(depositId);
        double interestRate = 0;
        for (DepositMonthRate depositMonthRate : depositMonthRates) {
            int monthLowerBound = depositMonthRate.getMonthLowerBound();
            int monthUpperBound = depositMonthRate.getMonthUpperBound();
            if (months >= monthLowerBound && months <= monthUpperBound) {
                interestRate = depositMonthRate.getInterestRate();
                break;
            }
        }

        if(interestRate == 0) {
            interestRate = depositMonthRates.get(depositMonthRates.size()-1).getInterestRate();
        }

        User user = userRepository.findOne(userId);
        Bank bank = bankService.getBankById(bankId);
        PutInDeposit putInDeposit = PutInDeposit.builder()
                                        .id(new UserBankId(userId, bankId))
                                        .user(user)
                                        .bank(bank)
                                        .deposit(deposit)
                                        .depositState(DepositState.ACTIVE)
                                        .recordState(RecordState.HAS_NOT_BEEN_READ)
                                        .amount(amount)
                                        .date(Date.valueOf(LocalDate.now()))
                                        .increasingDay(Date.valueOf(LocalDate.now().plusMonths(1)))
                                        .interestRate(interestRate)
                                        .months(months)
                                        .monthsLeft(months)
                                        .build();
        putInDepositService.savePutInDeposit(putInDeposit);

        Optional<UserBalanceBank> userBalanceBankCandidate = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(userId, bankId);
        UserBalanceBank userBalanceBank = userBalanceBankCandidate.get();
        long userBalance = userBalanceBank.getBalance();
        long newBalance = userBalance - amount;
        userBalanceBank.setBalance(newBalance);
        userBalanceBankService.saveUserBalanceBank(userBalanceBank);
    }

    @Override
    public void getDepositBack(Long userId, Long bankId) {
        Optional<UserBalanceBank> userBalanceBankCandidate = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(userId, bankId);
        Optional<PutInDeposit> putInDepositCandidate = putInDepositService.getPutInDepositByUserIdAndBankIdAndDepositState(
                userId, bankId, DepositState.ACTIVE);
        UserBalanceBank userBalanceBank = userBalanceBankCandidate.get();
        PutInDeposit putInDeposit = putInDepositCandidate.get();

        int amount = putInDeposit.getAmount();
        long userBalance = userBalanceBank.getBalance();
        long newBalance = userBalance + amount;
        userBalanceBank.setBalance(newBalance);
        userBalanceBankService.saveUserBalanceBank(userBalanceBank);

        putInDeposit.setDepositState(DepositState.HAS_BEEN_GET_BACK);
        putInDepositService.savePutInDeposit(putInDeposit);
    }

    @Override
    @Transactional
    public void makeTransfer(Long senderId, Long senderBankId, Long recipientBankId, String accountTo, int amount, Role recipientRole) {
        Optional<UserBalanceBank> userBalanceBank = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(senderId, senderBankId);
        UserBalanceBank senderBalanceBank = userBalanceBank.get();
        String accountFrom = senderBalanceBank.getCardId();
        Bank senderBank = bankService.getBankById(senderBankId);
        Bank recipientBank = bankService.getBankById(recipientBankId);

        Long recipientId = null;
        long recipientBalance = 0;
        UserBalanceBank recipientBalanceBank = null;
        if(recipientRole.equals(Role.ROLE_USER)) {
            List<UserBalanceBank> recipientBalanceBanks = userBalanceBankService.getAllUserBalanceBanks();
            for (UserBalanceBank recipBalanceBank : recipientBalanceBanks) {
                if(recipBalanceBank.getCardId().equals(accountTo)) {
                    recipientId = recipBalanceBank.getUser().getId();
                    recipientBalance = recipBalanceBank.getBalance();
                    recipientBalanceBank = recipBalanceBank;
                    break;
                }
            }
        } else {
            recipientId = senderBankId;
        }

        Transfer transfer = Transfer.builder()
                                .accountFrom(accountFrom)
                                .accountTo(accountTo)
                                .amount(amount)
                                .senderBank(senderBank)
                                .recipientBank(recipientBank)
                                .recipient(recipientRole)
                                .recipientId(recipientId)
                                .sender(Role.ROLE_USER)
                                .senderId(senderId)
                                .build();
        transferService.saveTransfer(transfer);

        long senderBalance = senderBalanceBank.getBalance();
        long newSenderBalance = senderBalance - amount;
        senderBalanceBank.setBalance(newSenderBalance);
        userBalanceBankService.saveUserBalanceBank(senderBalanceBank);

        if(recipientRole.equals(Role.ROLE_USER)) {
            long newRecipientBalance = recipientBalance + amount;
            recipientBalanceBank.setBalance(newRecipientBalance);
            userBalanceBankService.saveUserBalanceBank(recipientBalanceBank);
        }
    }

    @Override
    public void saveResponseState(AccountResponse accountResponse) {
        accountResponseService.saveAccountResponse(accountResponse);
    }

    @Override
    public User getUserById(Long userId) {
        User admin = userRepository.findOne(userId);
        return admin;
    }

    @Override
    public Long getUserId(Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Long id = details.getUser().getId();
        return id;
    }

    @Override
    public boolean isBalanceEnough(Long userId, Long bankId, int amount) {
        Optional<UserBalanceBank> userBalanceBank = userBalanceBankService.getUserBalanceBankByUserIdAndBankId(userId, bankId);
        if(userBalanceBank.isPresent()) {
            long userBalance = userBalanceBank.get().getBalance();
            if(userBalance >= amount) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isCardIdWrong(String cardId) {
        if(cardId.charAt(0) == '0') {
            return true;
        }

        for(int index=0; index<cardId.length(); index++) {
            if(cardId.charAt(index) < 48 || cardId.charAt(index) > 57) {
                return true;
            }
        }

        Optional<UserBalanceBank> userBalanceBank = userBalanceBankService.getUserBalanceBankByCardId(cardId);
        Optional<Bank> bank = bankService.getBankByAccount(cardId);
        if(userBalanceBank.isPresent() || bank.isPresent()) {
            return true;
        }

        return false;
    }
}
