package com.project.repositories;

import com.project.models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Optional<Deposit> findDepositByNameAndBankId(String name, Long bankId);
    List<Deposit> findAllByBankId(Long bankId);
}
