package com.project.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String sex;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String placeOfLiving;
    private String passportSerialNumber;
    private String mail;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserBalanceBank> bankBalance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AccountRequest> requests;
}
