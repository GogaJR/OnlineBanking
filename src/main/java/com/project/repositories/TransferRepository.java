package com.project.repositories;

import com.project.models.Role;
import com.project.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findAllBySenderIdAndSender(Long senderId, Role sender);
    List<Transfer> findAllByRecipientIdAndRecipient(Long recipientId, Role recipient);
    List<Transfer> findAllBySenderBankIdOrRecipientBankId(Long senderBankId, Long recipientBankId);
}
