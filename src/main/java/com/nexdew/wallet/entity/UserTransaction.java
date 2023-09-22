package com.nexdew.wallet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTransaction extends BaseEntity<Long> implements Serializable {

    @Column(nullable = false)
    private double amount;


    @OneToOne
    private Account toAccount;


    @OneToOne
    private Account fromAccount;


}
