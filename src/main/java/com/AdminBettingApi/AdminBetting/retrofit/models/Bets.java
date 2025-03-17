package com.AdminBettingApi.AdminBetting.retrofit.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Bets {

    private Long betId;
    private String betPlacedOn;
    private int totalGames;
    private Double stake;
    private Double totalOdds;
    private String status;
    private Long possibleWin;
}
