package com.project.repositories;

import com.project.joins.UserBankId;
import com.project.models.DepositState;
import com.project.models.PutInDeposit;
import com.project.models.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PutInDepositRepository extends JpaRepository<PutInDeposit, UserBankId> {
    Optional<PutInDeposit> findFirstByUserIdAndBankIdAndDepositState(Long userId, Long bankId, DepositState depositState);
    List<PutInDeposit> findAllByBankIdAndRecordState(Long bankId, RecordState recordState);
    List<PutInDeposit> findAllByUserIdAndDepositState(Long userId, DepositState depositState);
}
