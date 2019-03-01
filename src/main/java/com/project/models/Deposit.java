package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "deposit")
public class Deposit implements Comparable<Deposit> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int minAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL)
    private List<DepositMonthRate> depositMonthRates = new ArrayList<>();


    @Override
    public int compareTo(Deposit o) {
        if(this.id > o.id) return 1;
        else if(this.id < o.id) return -1;

        return 0;
    }
}
