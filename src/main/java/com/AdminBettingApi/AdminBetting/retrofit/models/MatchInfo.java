package com.AdminBettingApi.AdminBetting.retrofit.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MatchInfo {
    private String homeTeam;
    private String awayTeam;
    private Long matchId;
}
