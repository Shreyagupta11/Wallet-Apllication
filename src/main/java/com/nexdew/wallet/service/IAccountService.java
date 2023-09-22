package com.nexdew.wallet.service;

import com.nexdew.wallet.dto.AccountDto;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.AccountRequest;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.entity.User;

public interface IAccountService {
     AccountDto registeredUserAccount(AccountRequest request,String username);



}
