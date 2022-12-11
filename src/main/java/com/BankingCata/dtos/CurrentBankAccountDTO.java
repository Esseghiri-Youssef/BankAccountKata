package com.BankingCata.dtos;

import lombok.Data;

import java.util.Date;

import com.BankingCata.enums.AccountStatus;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
