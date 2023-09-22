package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.common.enums.AccType;
import com.nexdew.wallet.dto.AccountDto;
import com.nexdew.wallet.dto.request.AccountRequest;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.repository.AccountRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public AccountDto registeredUserAccount(AccountRequest request, String username) {

        if(userRepository.existsByUsername(username)) {
            String username1 = request.getUsername();
            Account account = Account.builder().accType(AccType.valueOf(request.getAccType())).openingBalance(request.getOpeningBalance())
                    .accountNumber(request.getAccountNumber()).description(request.getDescription())
                    .user(userRepository.findByUsername(username1)).build();

            System.out.println(account.toString());
            Account save = this.accountRepository.save(account);
            AccountDto accountDto = this.modelMapper.map(save, AccountDto.class);
            return accountDto;
        }

        return  null;
    }

}
