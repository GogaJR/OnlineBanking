package com.project.services;

import com.project.models.DepositState;
import com.project.models.PutInDeposit;
import com.project.models.RecordState;

import java.util.List;
import java.util.Optional;

public interface PutInDepositService {
    void savePutInDeposit(PutInDeposit putInDeposit);
    Optional<PutInDeposit> getPutInDepositByUserIdAndBankIdAndDepositState(Long userId, Long bankId, DepositState depositState);
    List<PutInDeposit> getPutInDepositsByUserId(Long userId);
    List<PutInDeposit> getPutInDepositsByBankIdAndRecordState(Long bankId, RecordState recordState);
}
