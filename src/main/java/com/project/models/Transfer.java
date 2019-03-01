package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long recipientId;
    private String accountFrom;
    private String accountTo;
    private int amount;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_bank_id")
    private Bank senderBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_bank_id")
    private Bank recipientBank;

    @Enumerated(value = EnumType.STRING)
    private Role sender;

    @Enumerated(value = EnumType.STRING)
    private Role recipient;
}
