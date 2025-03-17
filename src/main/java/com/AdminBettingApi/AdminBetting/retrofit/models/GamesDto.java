package com.AdminBettingApi.AdminBetting.retrofit.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class GamesDto {

    private Long matchId;
    private String homeTeam;
    private String awayTeam;
    private String startTime;
    private List<MarketDto> markets;
}
