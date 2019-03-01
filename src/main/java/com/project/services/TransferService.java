package com.project.services;

import com.project.models.Role;
import com.project.models.Transfer;
import com.project.transfer.TransferDto;

import java.util.List;

public interface TransferService {
    void saveTransfer(Transfer transfer);
    Role checkTransfer(Long userBankId, Long recipientBankId, String recipientName, String accountTo);
    List<TransferDto> getUserTransferDtosTo(Long userId, Role userRole);
    List<TransferDto> getUserTransferDtosFrom(Long userId, Role userRole);
    List<Transfer> getTransfersByBankId(Long bankId);
}
