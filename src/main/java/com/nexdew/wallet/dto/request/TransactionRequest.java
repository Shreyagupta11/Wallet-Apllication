package com.nexdew.wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    private String toAccount;


    private String fromAccount;


    private double Amount;
}
