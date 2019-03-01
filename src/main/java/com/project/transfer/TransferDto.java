package com.project.transfer;

import com.project.models.Transfer;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransferDto {
    private String accountTo;
    private String accountFrom;
    private int amount;
    private Timestamp date;

    public static TransferDto from(Transfer transfer) {
        return TransferDto.builder()
                .accountFrom(transfer.getAccountFrom())
                .accountTo(transfer.getAccountTo())
                .amount(transfer.getAmount())
                .date(transfer.getDate())
                .build();
    }
}
