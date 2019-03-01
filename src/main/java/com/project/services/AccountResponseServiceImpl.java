package com.project.services;

import com.project.models.AccountResponse;
import com.project.models.RecordState;
import com.project.repositories.AccountResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountResponseServiceImpl implements AccountResponseService {

    @Autowired
    private AccountResponseRepository accountResponseRepository;

    @Override
    public void saveAccountResponse(AccountResponse accountResponse) {
        accountResponseRepository.save(accountResponse);
    }

    @Override
    public List<AccountResponse> getAccountResponsesByUserIdAndRecordState(Long userId, RecordState recordState) {
        return accountResponseRepository.findAllByUserIdAndRecordState(userId, recordState);
    }
}
