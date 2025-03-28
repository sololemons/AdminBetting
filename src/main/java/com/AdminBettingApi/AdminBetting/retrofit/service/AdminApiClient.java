package com.AdminBettingApi.AdminBetting.retrofit.service;

import com.AdminBettingApi.AdminBetting.retrofit.models.*;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Optional;


public interface AdminApiClient {

    @GET("/transaction/get/history")
    Call <List<TransactionHistory>> getTransactions(@Header("Authorization") String authHeader);

    @GET("/transaction/all")
    Call <List<TransactionHistory>> getAllTransactions();
    @GET("users/alls")
    Call <List<User>> getAllUsers();
    @GET("/users/all")
    Call<PageResponse<User>> getAllUsers(
            @Query("page") int page,
            @Query("size") int size
    );

    @GET("/users/search")
    Call<PageResponse<User>> searchUsers(
            @Query("phoneNumber") String phoneNumber,
            @Query("page") int page,
            @Query("size") int size
    );
    @GET("/users/phoneNumber")
    Call<User> getUser(@Query("phoneNumber") String phoneNumber);
    @GET("/bets/get/id")
    Call<List<Bets>>getBetsById(@Query("id") Long id);
    @GET("/betslip/id")
    Call<List<BetSlip>> getBetSlipsById(@Query("betId") Long betId);

    @GET("/bets/all")
    Call<List<Bets>> getBets();
    @GET("/bets/count")
    Call <Double> getBetsCount();
    @GET("/bets/posibblewins")
    Call <Double> getPossibleWins();
    @GET("/betslip/user/bet/{betId}")
    Call<List<BetSlip>> getBetSlipsByUserAndBetId(
            @Header("Authorization") String authHeader,
            @Path("betId") Long betId
    );
    @GET("/games/all")
    Call <List<GamesDto>> getAllGames();
    @GET("/transaction/get/id")
    Call <List<TransactionHistory>> getTransactionsByTransactionId(@Query("id") Long id);
    @GET("/transaction/search/transactionRef")
    Call <List<TransactionHistory>> getTransactionsByTransactionRef(@Query("transactionRef") String transactionRef);
    @GET("/betslip/all")
    Call<List<BetSlip>>getAllBetsSlips();
}
