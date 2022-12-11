package com.BankingCata.services;

import java.util.List;
import com.BankingCata.dtos.AccountHistoryDTO;
import com.BankingCata.dtos.AccountOperationDTO;
import com.BankingCata.dtos.BankAccountDTO;
import com.BankingCata.dtos.CurrentBankAccountDTO;
import com.BankingCata.dtos.CustomerDTO;
import com.BankingCata.dtos.SavingBankAccountDTO;
import com.BankingCata.exceptions.BalanceNotSufficientException;
import com.BankingCata.exceptions.BankAccountNotFoundException;
import com.BankingCata.exceptions.CustomerNotFoundException;
public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void withdrawal(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void deposit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;


    List<AccountOperationDTO> accountHistory(String accountId);
    
    List<BankAccountDTO> bankAccountList();


}
