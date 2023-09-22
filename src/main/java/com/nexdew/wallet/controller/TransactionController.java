package com.nexdew.wallet.controller;

import com.nexdew.wallet.constants.ApiConstant;
import com.nexdew.wallet.dto.TransactionDto;
import com.nexdew.wallet.dto.request.TransactionRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users/account/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final ITransactionService transactionService;

//    @PostMapping("/transfer")
//    public ResponseEntity<ApiResponse> fundTransfer(@Valid @RequestBody TransactionRequest request, Principal principal) {
//        String uname = principal.getName();
//
//        System.out.println("Current user is: " + uname);
//        return ResponseEntity.ok(new ApiResponse(ApiConstant.SUCCESS_RESPONSE, transactionService.fundTransfer(request, uname), HttpStatus.OK));
//    }


    @PostMapping("/transactions")
    public ResponseEntity<ApiResponse> makeTransaction(@Valid @RequestBody TransactionRequest transactionRequest , Principal principal) {
        try {

            String uname = principal.getName();
            TransactionDto transactionDto = transactionService.makeTransaction(transactionRequest.getToAccount(), transactionRequest.getFromAccount(), transactionRequest.getAmount(), uname);
            return ResponseEntity.ok(new ApiResponse(ApiConstant.SUCCESS_RESPONSE,transactionDto,HttpStatus.OK));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(new ApiResponse(ApiConstant.NO_DATA_FOUND_RESPONSE,null,HttpStatus.NOT_FOUND));
        }

    }
}