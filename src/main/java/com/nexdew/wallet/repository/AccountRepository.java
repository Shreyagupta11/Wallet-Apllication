package com.nexdew.wallet.repository;

import com.nexdew.wallet.entity.Account;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends BaseRepository<Account,Integer>{

    @Query("SELECT MAX(a.accountNumber) FROM Account a")
    String findHighestAccountNumber();

    Account findByAccountNumber(String accountNumber);

}
