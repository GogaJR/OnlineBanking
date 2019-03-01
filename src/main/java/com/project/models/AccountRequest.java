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
@Table(name = "account_request")
public class AccountRequest {
    @EmbeddedId
    private UserBankId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bankId")
    private Bank bank;

    private String cardId;

    @Enumerated(value = EnumType.STRING)
    private Response response;
}
