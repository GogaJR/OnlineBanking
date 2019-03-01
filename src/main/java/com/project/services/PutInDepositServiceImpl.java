package com.project.services;

import com.project.models.DepositState;
import com.project.models.PutInDeposit;
import com.project.models.RecordState;
import com.project.repositories.PutInDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PutInDepositServiceImpl implements PutInDepositService {

    @Autowired
    public PutInDepositRepository putInDepositRepository;

    @Override
    public Optional<PutInDeposit> getPutInDepositByUserIdAndBankIdAndDepositState(Long userId, Long bankId, DepositState depositState) {
        return putInDepositRepository.findFirstByUserIdAndBankIdAndDepositState(userId, bankId, depositState);
    }

    @Override
    public List<PutInDeposit> getPutInDepositsByUserId(Long userId) {
        return putInDepositRepository.findAllByUserIdAndDepositState(userId, DepositState.ACTIVE);
    }

    @Override
    public List<PutInDeposit> getPutInDepositsByBankIdAndRecordState(Long bankId, RecordState recordState) {
        return putInDepositRepository.findAllByBankIdAndRecordState(bankId, recordState);
    }

    @Override
    public void savePutInDeposit(PutInDeposit putInDeposit) {
        putInDepositRepository.save(putInDeposit);
    }
}
