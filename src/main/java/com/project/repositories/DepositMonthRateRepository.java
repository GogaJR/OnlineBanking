package com.project.repositories;

import com.project.models.DepositMonthRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositMonthRateRepository extends JpaRepository<DepositMonthRate, Long> {
    List<DepositMonthRate> findAllByDepositId(Long depositId);
}
