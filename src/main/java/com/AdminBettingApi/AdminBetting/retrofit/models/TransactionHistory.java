package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

@Data
public class TransactionHistory {
    private double amount;
    private String phoneNumber;
    private String transactionRef;
    private String transactionType;
    private String transactionDate;
}
