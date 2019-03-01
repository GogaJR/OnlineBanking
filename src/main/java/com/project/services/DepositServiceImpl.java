package com.project.services;

import com.project.models.Deposit;
import com.project.models.DepositMonthRate;
import com.project.models.DepositState;
import com.project.models.PutInDeposit;
import com.project.repositories.DepositMonthRateRepository;
import com.project.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private PutInDepositService putInDepositService;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositMonthRateRepository depositMonthRateRepository;

    @Override
    public void saveDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }

    @Override
    public boolean isDepositAlreadyPutIn(Long userId, Long bankId) {
        Optional<PutInDeposit> putInDeposit = putInDepositService.getPutInDepositByUserIdAndBankIdAndDepositState(userId, bankId, DepositState.ACTIVE);
        if(putInDeposit.isPresent()) {
            return true;
        }

        return false;
    }

    @Override
    public Deposit getDepositById(Long depositId) {
        return depositRepository.findOne(depositId);
    }

    @Override
    public Optional<Deposit> getDepositByNameAndBankId(String depopsitName, Long bankId) {
        return depositRepository.findDepositByNameAndBankId(depopsitName, bankId);
    }

    @Override
    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    @Override
    public Map<Deposit, List<DepositMonthRate>> getBankDepositAndMonthRateList(Long bankId) {
        Map<Deposit, List<DepositMonthRate>> depositWithMonthRates = new TreeMap<>();
        List<Deposit> deposits = depositRepository.findAllByBankId(bankId);
        for(Deposit deposit : deposits) {
            Long depositId = deposit.getId();
            List<DepositMonthRate> depositMonthRates = depositMonthRateRepository.findAllByDepositId(depositId);
            depositWithMonthRates.put(deposit, depositMonthRates);
        }

        return depositWithMonthRates;
    }
}
