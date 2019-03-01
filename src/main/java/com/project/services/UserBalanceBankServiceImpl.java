package com.project.services;

import com.project.models.UserBalanceBank;
import com.project.repositories.UserBalanceBankRepository;
import com.project.transfer.UserBalanceBankDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBalanceBankServiceImpl implements UserBalanceBankService {

    @Autowired
    private UserBalanceBankRepository userBalanceBankRepository;

    @Override
    public Optional<UserBalanceBank> getUserBalanceBankByUserId(Long userId) {
        Optional<UserBalanceBank> userBalanceBankCandidate = userBalanceBankRepository.findFirstByUserId(userId);
        return userBalanceBankCandidate;
    }

    @Override
    public Optional<UserBalanceBank> getUserBalanceBankByUserIdAndBankId(Long userId, Long bankId) {
        Optional<UserBalanceBank> userBalanceBankCandidate = userBalanceBankRepository.findFirstByUserIdAndBankId(userId, bankId);
        return userBalanceBankCandidate;
    }

    @Override
    public Optional<UserBalanceBank> getUserBalanceBankByCardId(String cardId) {
        return userBalanceBankRepository.findByCardId(cardId);
    }

    @Override
    public List<UserBalanceBankDto> getUserBalanceBankDtosByUserId(Long userId) {
        List<UserBalanceBank> userBalanceBanks = userBalanceBankRepository.findAllByUserId(userId);

        List<UserBalanceBankDto> userBalanceBankDtos = new ArrayList<>();
        for(UserBalanceBank userBalanceBank : userBalanceBanks) {
            UserBalanceBankDto userBalanceBankDto = UserBalanceBankDto.withBankNameAndBalanceFrom(userBalanceBank);
            userBalanceBankDtos.add(userBalanceBankDto);
        }

        return userBalanceBankDtos;
    }

    @Override
    public List<UserBalanceBank> getAllUserBalanceBanks() {
        return userBalanceBankRepository.findAll();
    }

    @Override
    public List<UserBalanceBank> getUserBalanceBanksByBankId(Long bankId) {
        return userBalanceBankRepository.findAllByBankId(bankId);
    }

    @Override
    public List<UserBalanceBank> getUserBalanceBanksByUserId(Long userId) {
        return userBalanceBankRepository.findAllByUserId(userId);
    }

    @Override
    public void saveUserBalanceBank(UserBalanceBank userBalanceBank) {
        userBalanceBankRepository.save(userBalanceBank);
    }
}
