package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

@Data
public class User {
    private double accountBalance;
    private Long id;
    private String phoneNumber;
}
