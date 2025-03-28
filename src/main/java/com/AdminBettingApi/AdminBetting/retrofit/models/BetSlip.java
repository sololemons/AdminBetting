package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

@Data
public class BetSlip {

    private Long betSlipId;
    private MatchInfo  matchInfo;
    private String market;
    private String pick;
    private double odds;
    private String status;
    private String betSlipPlacedOn;
}
