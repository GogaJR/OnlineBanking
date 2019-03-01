package com.project.services;

import com.project.joins.UserBankId;
import com.project.models.AccountRequest;
import com.project.models.Response;
import com.project.repositories.AccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestServiceImpl implements AccountRequestService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    @Override
    public Optional<AccountRequest> getAccountRequestByUserIdAndBankIdAndCardId(Long userId, Long bankId, String cardId) {
        Optional<AccountRequest> accountRequestCandidate = accountRequestRepository.findOneByUserIdAndCardIdAndBankId(userId, cardId, bankId);
        return accountRequestCandidate;
    }

    @Override
    public AccountRequest getAccountRequestByUserBankId(UserBankId userBankId) {
        return accountRequestRepository.findOne(userBankId);
    }

    @Override
    public List<AccountRequest> getAccountRequestsByBankIdAndResponse(Long bankId, Response response) {
        return accountRequestRepository.findAllByBankIdAndResponse(bankId, response);
    }

    @Override
    public void saveAccountRequest(AccountRequest accountRequest) {
        accountRequestRepository.save(accountRequest);
    }
}
