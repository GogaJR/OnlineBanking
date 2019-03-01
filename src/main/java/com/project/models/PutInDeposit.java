package com.project.models;

import com.project.joins.UserBankId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "put_in_deposit")
public class PutInDeposit {

    @EmbeddedId
    private UserBankId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bankId")
    private Bank bank;

    private int amount;
    private int months;
    private Date date;
    private double interestRate;
    private Date increasingDay;
    private int monthsLeft;

    @Enumerated(value = EnumType.STRING)
    private DepositState depositState;

    @Enumerated(value = EnumType.STRING)
    private RecordState recordState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;
}
