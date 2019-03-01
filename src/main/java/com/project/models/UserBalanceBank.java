package com.project.models;

import com.project.joins.UserBankId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_balance_bank")
public class UserBalanceBank {

    @EmbeddedId
    private UserBankId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bankId")
    private Bank bank;

    private String cardId;

    private long balance;
}
