package com.project.services;

import com.project.models.AccountResponse;
import com.project.models.RecordState;

import java.util.List;

public interface AccountResponseService {
    void saveAccountResponse(AccountResponse accountResponse);
    List<AccountResponse> getAccountResponsesByUserIdAndRecordState(Long userId, RecordState recordState);
}
