package com.nexdew.wallet.controller;


import com.nexdew.wallet.constants.ApiConstant;
import com.nexdew.wallet.dto.request.AccountRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final IAccountService accountService;

    @PostMapping("/registered-create")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public ResponseEntity<ApiResponse> registeredUserBankAccount(@Valid @RequestBody AccountRequest request, Principal principal)  {
        String uname = principal.getName();
        return  ResponseEntity.ok(new ApiResponse(ApiConstant.ACCOUNT_CREATED, accountService.registeredUserAccount(request , uname), HttpStatus.CREATED));
    }
}
