package com.project.repositories;

import com.project.joins.UserBankId;
import com.project.models.AccountResponse;
import com.project.models.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountResponseRepository extends JpaRepository<AccountResponse, UserBankId> {
    List<AccountResponse> findAllByUserIdAndRecordState(Long userId, RecordState recordState);
}
