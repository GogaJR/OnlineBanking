package com.project.repositories;

import com.project.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findOneByMail(String mail);
    Optional<Bank> findByAccount(String account);
}
