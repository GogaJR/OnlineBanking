package com.project.services;

import com.project.models.Deposit;
import com.project.models.DepositMonthRate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepositService {
    void saveDeposit(Deposit deposit);
    boolean isDepositAlreadyPutIn(Long userId, Long bankId);
    Optional<Deposit> getDepositByNameAndBankId(String depopsitName, Long bankId);
    Deposit getDepositById(Long depositId);
    List<Deposit> getAllDeposits();
    Map<Deposit, List<DepositMonthRate>> getBankDepositAndMonthRateList(Long bankId);
}
