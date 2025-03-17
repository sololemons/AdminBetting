package com.AdminBettingApi.AdminBetting.retrofit.models;
import lombok.Data;

import java.util.List;
@Data
public class MarketDto {
    private Long marketId;
    private String marketName;
    private List<OddDto> oddsList;
}
