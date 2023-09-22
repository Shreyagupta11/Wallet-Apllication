package com.nexdew.wallet.dto;

import com.nexdew.wallet.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

private String customer_Id;
private String username;
private String contact;
private String email;
private String gender;
private String appUserRoles;

private List<Account> accounts;
}
