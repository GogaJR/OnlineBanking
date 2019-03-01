package com.project.transfer;

import com.project.models.Bank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDto {
    private Long id;
    private String name;

    public static BankDto from(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .build();
    }
}
