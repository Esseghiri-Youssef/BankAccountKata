package com.BankingCata.dtos;

import lombok.Data;

@Data
public class WithdrawalDTO {
    private String accountId;
    private double amount;
    private String description;
}
