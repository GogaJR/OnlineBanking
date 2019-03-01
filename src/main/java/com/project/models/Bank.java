package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String fax;
    private String mail;
    private String hashPassword;
    private String workingHours;
    private String daysOff;
    private String account;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<UserBalanceBank> userBalance;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<AccountRequest> requests;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Deposit> deposits;

    @OneToMany(mappedBy = "senderBank", cascade = CascadeType.ALL)
    private List<Transfer> senderTransfers;

    @OneToMany(mappedBy = "recipientBank", cascade = CascadeType.ALL)
    private List<Transfer> recipientTransfers;
}
