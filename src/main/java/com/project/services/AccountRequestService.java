package com.project.services;

import com.project.joins.UserBankId;
import com.project.models.AccountRequest;
import com.project.models.Response;

import java.util.List;
import java.util.Optional;

public interface AccountRequestService {
    void saveAccountRequest(AccountRequest accountRequest);
    Optional<AccountRequest> getAccountRequestByUserIdAndBankIdAndCardId(Long userId, Long bankId, String cardId);
    AccountRequest getAccountRequestByUserBankId(UserBankId userBankId);
    List<AccountRequest> getAccountRequestsByBankIdAndResponse(Long bankId, Response response);
}
