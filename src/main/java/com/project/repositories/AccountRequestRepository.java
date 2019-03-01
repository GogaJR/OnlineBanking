package com.project.repositories;

import com.project.joins.UserBankId;
import com.project.models.AccountRequest;
import com.project.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, UserBankId> {
    Optional<AccountRequest> findOneByUserIdAndCardIdAndBankId(Long userId, String cardId, Long bankId);
    List<AccountRequest> findAllByBankIdAndResponse(Long bankId, Response response);
}
