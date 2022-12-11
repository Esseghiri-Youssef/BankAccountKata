package com.BankingCata.dtos;

import lombok.Data;

@Data
public class DepositDTO {
    private String accountId;
    private double amount;
    private String description;
}
