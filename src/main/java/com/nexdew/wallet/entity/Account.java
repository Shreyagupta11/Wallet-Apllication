package com.nexdew.wallet.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexdew.wallet.common.enums.AccType;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity<Long> implements Serializable {

    @Column(unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccType accType;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column
    private double openingBalance;

    @Column
    private String description;



    public Account(String description, AccType accType, double openingBalance) {
        super();
    }

    public Account(String accountNumber){
       this.accountNumber=accountNumber;
    }

}
