package com.project.transfer;

import com.project.models.UserBalanceBank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBalanceBankDto {
    private Long userId;
    private String userName;
    private String userSurname;
    private String bankName;
    private String cardId;
    private long balance;

    public static UserBalanceBankDto from(UserBalanceBank userBalanceBank) {
        return UserBalanceBankDto.builder()
                .userId(userBalanceBank.getUser().getId())
                .userName(userBalanceBank.getUser().getName())
                .userSurname(userBalanceBank.getUser().getSurname())
                .balance(userBalanceBank.getBalance())
                .build();
    }

    public static UserBalanceBankDto withBankNameAndBalanceFrom(UserBalanceBank userBalanceBank) {
        return UserBalanceBankDto.builder()
                .bankName(userBalanceBank.getBank().getName())
                .balance(userBalanceBank.getBalance())
                .cardId(userBalanceBank.getCardId())
                .build();
    }
}
