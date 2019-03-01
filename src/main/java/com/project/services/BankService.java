package com.project.services;

import com.project.models.*;
import com.project.transfer.BankDto;
import com.project.transfer.TransferDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface BankService {
    void increaseDeposit(PutInDeposit putInDeposit);
    void sendResponse(Long userId, Long bankId, String cardId, Response response, long userBalance);
    void addDeposit(String depositName, int minAmount, Long bankId, List<Integer> monthLowerBounds, List<Integer> monthUpperBounds,
                    List<Double> interestRates);
    void saveDepositRecordState(PutInDeposit putInDeposit);
    Long getBankId(Authentication authentication);
    Bank getBankById(Long bankId);
    Optional<Bank> getBankByAccount(String account);
    List<Bank> getAllBanks();
    List<Bank> getAllBanksByUserId(Long userId);
    List<BankDto> getBankDtosByUserId(Long userId);
}
