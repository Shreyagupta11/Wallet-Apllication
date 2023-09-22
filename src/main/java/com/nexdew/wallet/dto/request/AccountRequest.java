package com.nexdew.wallet.dto.request;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.common.enums.Gender;
import com.nexdew.wallet.configuration.anotationValidate.ValueOfEnum;
import com.nexdew.wallet.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest
{

private String accountNumber;

@ValueOfEnum(enumClass = AccType.class)
private String accType;

@NotNull(message = "Description cannot be null")
@NotBlank(message = "description cannot be blank")
private  String description;

@NotNull(message = "Please enter your balance")
private double openingBalance;

private String username;

}
