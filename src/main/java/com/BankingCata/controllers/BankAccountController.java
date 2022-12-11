package com.BankingCata.controllers;

import org.springframework.web.bind.annotation.*;

import com.BankingCata.dtos.AccountHistoryDTO;
import com.BankingCata.dtos.AccountOperationDTO;
import com.BankingCata.dtos.BankAccountDTO;
import com.BankingCata.dtos.DepositDTO;
import com.BankingCata.dtos.WithdrawalDTO;
import com.BankingCata.exceptions.BalanceNotSufficientException;
import com.BankingCata.exceptions.BankAccountNotFoundException;
import com.BankingCata.services.BankAccountService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BankAccountController {
    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    //get a banck account by ID
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    //get all operations of an account
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    //make a withdrawal
    @PostMapping("/accounts/withdrawal")
    public WithdrawalDTO withdrawal(@RequestBody WithdrawalDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.withdrawal(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    
    //make a deposit
    @PostMapping("/accounts/deposit")
    public DepositDTO deposit(@RequestBody DepositDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.deposit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
}
