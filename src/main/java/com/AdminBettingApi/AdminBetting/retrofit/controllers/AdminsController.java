package com.AdminBettingApi.AdminBetting.retrofit.controllers;

import com.AdminBettingApi.AdminBetting.retrofit.models.*;
import com.AdminBettingApi.AdminBetting.retrofit.service.TransactionServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/admins")
public class AdminsController {

    private final TransactionServiceClient transactionService;

    public AdminsController(TransactionServiceClient transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/fetch/all")
    public List<TransactionHistory> getAllTransactions() {


        return transactionService.fetchAllTransactionHistory();
    }

    @GetMapping("/betId")
    public List<BetSlip> getBetSlips(@RequestParam Long betId) {

        return transactionService.fetchBetSlipsByBetId(betId);
    }


    @GetMapping("/all/bets")
    public List<Bets> getAllBets() {
        return transactionService.fetchBets();
    }

    @GetMapping("/games")
    public List<GamesDto> getAllGames() {
        return transactionService.fetchAllGames();
    }

    @GetMapping("/all/users")
    public PageResponse<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return transactionService.fetchAllUsers(page, size);
    }

    @GetMapping("/search")
    public PageResponse<User> searchByPhoneNumber(@RequestParam String phoneNumber,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return transactionService.searchUsersByPhoneNumber(phoneNumber, page, size);
    }

    @GetMapping("/get")
    public User getByPhoneNumber(@RequestParam String phoneNumber) {
        System.out.println("Received" + phoneNumber);
        return transactionService.searchUserByPhoneNumber(phoneNumber);

    }

    @GetMapping("get/bets")
    public List<Bets> getBetsById(@RequestParam Long id) {
        return transactionService.searchBetsById(id);
    }

    @GetMapping("/get/transactions")
    public List<TransactionHistory> getTransactionsById(@RequestParam Long id) {
        return transactionService.fetchTransactionsById(id);

    }

    @GetMapping("/search/transactionRef")
    public List<TransactionHistory> searchTransactionRef(@RequestParam String transactionRef) {
        return transactionService.searchTransactionsByTransactionRef(transactionRef);
    }

    @GetMapping("/all/user")
    public List<User> getUsers() {
        return transactionService.getUsers();
    }
    @GetMapping("/all/betslips")
    public List<BetSlip> getBetsSlips() {
        return transactionService.getAllBetsSlips();
    }
}


