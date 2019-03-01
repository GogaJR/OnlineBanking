package com.project.services;

import com.project.models.*;
import com.project.transfer.BankDto;
import com.project.transfer.TransferDto;
import com.project.transfer.UserBalanceBankDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    void sendRequest(Long userId, Long bankId, String cardId);
    void putInDeposit(Long userId, Long bankId, Long depositId, int amount, int months);
    void getDepositBack(Long userId, Long bankId);
    void makeTransfer(Long senderId, Long senderBankId, Long recipientBankId, String accountTo, int amount, Role recipientRole);
    void saveResponseState(AccountResponse accountResponse);
    boolean isCardIdWrong(String cardId);
    boolean isBalanceEnough(Long userId, Long bankId, int amount);
    Long getUserId(Authentication authentication);
    User getUserById(Long userId);
}
