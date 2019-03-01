package com.project.transfer;

import com.project.models.Bank;
import com.project.models.DepositState;
import com.project.models.PutInDeposit;
import com.project.models.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class PutInDepositDto {
    private UserDto userDto;
    private BankDto bankDto;
    private int amount;
    private Date date;
    private Date increasingDay;
    private double interestRate;
    private int months;
    private int monthsLeft;
    private DepositState depositState;

    public static PutInDepositDto forBank(PutInDeposit putInDeposit) {
        User user = putInDeposit.getUser();
        return PutInDepositDto.builder()
                .userDto(UserDto.from(user))
                .amount(putInDeposit.getAmount())
                .date(putInDeposit.getDate())
                .increasingDay(putInDeposit.getIncreasingDay())
                .interestRate(putInDeposit.getInterestRate())
                .months(putInDeposit.getMonths())
                .monthsLeft(putInDeposit.getMonthsLeft())
                .depositState(putInDeposit.getDepositState())
                .build();
    }

    public static PutInDepositDto forUser(PutInDeposit putInDeposit) {
        Bank bank = putInDeposit.getBank();
        return PutInDepositDto.builder()
                .bankDto(BankDto.from(bank))
                .amount(putInDeposit.getAmount())
                .date(putInDeposit.getDate())
                .increasingDay(putInDeposit.getIncreasingDay())
                .interestRate(putInDeposit.getInterestRate())
                .months(putInDeposit.getMonths())
                .monthsLeft(putInDeposit.getMonthsLeft())
                .build();
    }
}
