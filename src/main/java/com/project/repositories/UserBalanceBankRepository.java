package com.project.repositories;

import com.project.joins.UserBankId;
import com.project.models.UserBalanceBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBalanceBankRepository extends JpaRepository<UserBalanceBank, UserBankId> {
    Optional<UserBalanceBank> findFirstByUserId(Long userId);
    Optional<UserBalanceBank> findFirstByUserIdAndBankId(Long userId, Long bankId);
    Optional<UserBalanceBank> findByCardId(String cardId);
    List<UserBalanceBank> findAllByBankId(Long bankId);
    List<UserBalanceBank> findAllByUserId(Long userId);
}
