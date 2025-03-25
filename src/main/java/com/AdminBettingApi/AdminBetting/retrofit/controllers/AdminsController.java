package com.AdminBettingApi.AdminBetting.retrofit.controllers;

import com.AdminBettingApi.AdminBetting.retrofit.models.*;
import com.AdminBettingApi.AdminBetting.retrofit.service.TransactionServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")

public class AdminsController {
    private static final Logger logger = LoggerFactory.getLogger(AdminsController.class);
    TransactionServiceClient transactionService = new TransactionServiceClient();

    @GetMapping("/fetch")
    public List<TransactionHistory> getBettingTransactions(@RequestHeader("Authorization") String authHeader) {
        logger.info(" Received Authorization Header: {}", authHeader);


        return transactionService.fetchTransactionHistory(authHeader);
    }

    @GetMapping("/fetch/all")
    public List<TransactionHistory> getAllTransactions() {


        return transactionService.fetchAllTransactionHistory();
    }
    @GetMapping("/betId")
    public List<BetSlip> getBetslips(@RequestParam Long betId){

        return transactionService.fetchBetSlipsByBetId(betId);
    }




    @GetMapping("/all/bets")
    public List<Bets> getAllBets() {
        return transactionService.fetchBets();
    }

    @GetMapping("/betcount")
    public double getBetCounts() {
        return transactionService.fetchBetCount();
    }

    @GetMapping("/possiblewins")
    public double getPossibleWins() {
        return transactionService.fetchPossibleWinSum();
    }

    @GetMapping("/betslips/{betId}")
    public List<BetSlip> getAllBetSlips(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long betId
    ) {
        return transactionService.fetchBetSlipsByPhoneNumber(authHeader, betId);

    }

    @GetMapping("/games")
    public List<GamesDto> getAllGames() {
        return transactionService.fetchAllGames();
    }
    @GetMapping("/all/users")
    public PageResponse<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        return transactionService.fetchAllUsers(page,size);
    }
   @GetMapping("/search")
    public PageResponse<User> searchByPhoneNumber(@RequestParam String phoneNumber,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return transactionService.searchUsersByPhoneNumber(phoneNumber,page,size);
}
@GetMapping("/get")
    public User getByPhoneNumber(@RequestParam String phoneNumber) {
    System.out.println("Received" + phoneNumber);
        return transactionService.searchUserByPhoneNumber(phoneNumber);

}
@GetMapping("get/bets")
    public List<Bets> getBetsById(@RequestParam Long id){
        return transactionService.searchBetsById(id);
}

}
