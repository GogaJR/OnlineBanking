package com.project.services;

import com.project.models.UserBalanceBank;
import com.project.transfer.UserBalanceBankDto;

import java.util.List;
import java.util.Optional;

public interface UserBalanceBankService {
    void saveUserBalanceBank(UserBalanceBank userBalanceBank);
    Optional<UserBalanceBank> getUserBalanceBankByUserId(Long userId);
    Optional<UserBalanceBank> getUserBalanceBankByUserIdAndBankId(Long userId, Long bankId);
    Optional<UserBalanceBank> getUserBalanceBankByCardId(String cardId);
    List<UserBalanceBankDto> getUserBalanceBankDtosByUserId(Long userId);
    List<UserBalanceBank> getAllUserBalanceBanks();
    List<UserBalanceBank> getUserBalanceBanksByBankId(Long bankId);
    List<UserBalanceBank> getUserBalanceBanksByUserId(Long userId);
}
