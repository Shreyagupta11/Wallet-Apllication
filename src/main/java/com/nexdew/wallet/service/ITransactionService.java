package com.nexdew.wallet.service;

import com.nexdew.wallet.dto.TransactionDto;
import com.nexdew.wallet.dto.request.TransactionRequest;

public interface ITransactionService {

//    TransactionDto fundTransfer(TransactionRequest request,String username);
 TransactionDto makeTransaction(String fromAccountNumber, String toAccountNumber, double amount,String username);
}
