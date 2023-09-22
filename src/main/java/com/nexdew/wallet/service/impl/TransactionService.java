package com.nexdew.wallet.service.impl;

import com.nexdew.wallet.dto.TransactionDto;
import com.nexdew.wallet.entity.Account;
import com.nexdew.wallet.entity.UserTransaction;
import com.nexdew.wallet.repository.AccountRepository;
import com.nexdew.wallet.repository.TransactionRepository;
import com.nexdew.wallet.repository.UserRepository;
import com.nexdew.wallet.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class
TransactionService implements ITransactionService {


private final UserRepository userRepository;
private final AccountRepository accountRepository;
private final TransactionRepository transactionRepository;
private final ModelMapper modelMapper;


    @Override
//    public TransactionDto fundTransfer(TransactionRequest request,String username) {
//        if(userRepository.existsByUsername(username)){
//            Account toAccount = new Account(request.getToAccount());
//            Account fromAccount = new Account(request.getFromAccount());
//            Ttransaction ttransaction = Ttransaction.builder().toAccount(toAccount).fromAccount(fromAccount).amount(request.getAmount()).build();
//
//            Ttransaction save = transactionRepository.save(ttransaction);
//            TransactionDto transactionDto = modelMapper.map(save, TransactionDto.class);
//            return transactionDto;
//        }
//        return null;
//    }

    @Transactional
    public TransactionDto makeTransaction(String fromAccountNumber, String toAccountNumber, double amount,String username) {

        Account fromfromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account totoAccount = accountRepository.findByAccountNumber(toAccountNumber);

        UserTransaction userTransaction = UserTransaction.builder().toAccount(totoAccount).fromAccount(fromfromAccount).amount(amount).build();


        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account numbers provided.");
        }

        double fromAccountBalance = fromAccount.getOpeningBalance();
        double toAccountBalance = toAccount.getOpeningBalance();

        if (fromAccountBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance in the from account.");
        }

        double subAmount = fromAccountBalance + amount;
        fromAccount.setOpeningBalance(subAmount);

        double addAmount = toAccountBalance - amount;
        toAccount.setOpeningBalance(addAmount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        UserTransaction save = transactionRepository.save(userTransaction);
        TransactionDto transactionDto = modelMapper.map(save, TransactionDto.class);
        return transactionDto;


    }


}



