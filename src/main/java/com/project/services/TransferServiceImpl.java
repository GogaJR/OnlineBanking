package com.project.services;

import com.project.models.*;
import com.project.repositories.BankRepository;
import com.project.repositories.TransferRepository;
import com.project.repositories.UserBalanceBankRepository;
import com.project.transfer.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private UserBalanceBankRepository userBalanceBankRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public void saveTransfer(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public Role checkTransfer(Long userBankId, Long recipientBankId, String recipientName, String accountTo) {
        List<UserBalanceBank> userBalanceBanks = userBalanceBankRepository.findAll();
        for(UserBalanceBank userBalanceBank : userBalanceBanks) {
            String cardId = userBalanceBank.getCardId();
            Long bankId = userBalanceBank.getBank().getId();
            if(cardId.equals(accountTo) && recipientBankId.equals(bankId)) {
                User recipientUser = userBalanceBank.getUser();
                String userName = recipientUser.getName();
                if(userName.toLowerCase().equals(recipientName.toLowerCase())) {
                    return Role.ROLE_USER;
                }

                return null;
            }
        }

        Bank bank = bankRepository.findOne(userBankId);
        String bankAccount = bank.getAccount();
        String bankName = bank.getName();
        if(bankAccount.equals(accountTo) && bankName.toLowerCase().equals(recipientName.toLowerCase())) {
            return Role.ROLE_BANK;
        }

        return null;
    }

    @Override
    public List<TransferDto> getUserTransferDtosTo(Long userId, Role userRole) {
        List<Transfer> userTransfers = transferRepository.findAllBySenderIdAndSender(userId, userRole);
        List<TransferDto> userTransferDtos = transferDtos(userTransfers);

        return userTransferDtos;
    }

    @Override
    public List<TransferDto> getUserTransferDtosFrom(Long userId, Role userRole) {
        List<Transfer> userTransfers = transferRepository.findAllByRecipientIdAndRecipient(userId, userRole);
        List<TransferDto> userTransferDtos = transferDtos(userTransfers);

        return userTransferDtos;
    }

    @Override
    public List<Transfer> getTransfersByBankId(Long bankId) {
        return transferRepository.findAllBySenderBankIdOrRecipientBankId(bankId, bankId);
    }

    private List<TransferDto> transferDtos(List<Transfer> transfers) {
        List<TransferDto> transferDtos = new ArrayList<>();
        for (Transfer transfer : transfers) {
            TransferDto transferDto = TransferDto.from(transfer);
            transferDtos.add(transferDto);
        }

        return transferDtos;
    }
}
