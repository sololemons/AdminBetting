package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

@Data
public class TransactionHistory {
    private double amount;
    private String transactionRef;
    private String transactionType;
}
