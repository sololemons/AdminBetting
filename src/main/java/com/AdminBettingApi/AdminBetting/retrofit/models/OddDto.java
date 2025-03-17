package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

@Data
public class OddDto {
    private Long oddsId;
    private String oddType;
    private double oddsValue;

}
