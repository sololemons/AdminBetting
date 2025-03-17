package com.AdminBettingApi.AdminBetting.retrofit.service;

import com.AdminBettingApi.AdminBetting.retrofit.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TransactionServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceClient.class);
    private final AdminApiClient apiClient;

    public TransactionServiceClient() {
        this.apiClient = RetrofitClient.getClient().create(AdminApiClient.class);
    }

    public List<TransactionHistory> fetchAllTransactionHistory() {
        Call<List<TransactionHistory>> call = apiClient.getAllTransactions();
        try {
            Response<List<TransactionHistory>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info(" Successfully fetched {} transactions.", response.body().size());
                return response.body();
            } else {
                logger.error("Failed to fetch transactions. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error(" API call failed due to network error: {}", e.getMessage());
        }
        return null;
    }

    public List<TransactionHistory> fetchTransactionHistory(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        logger.info("Sending Authorization Header to Betting API: {}", authHeader);

        Call<List<TransactionHistory>> call = apiClient.getTransactions(authHeader);

        try {
            Response<List<TransactionHistory>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} transactions from Betting API.", response.body().size());
                return response.body();
            } else {
                logger.error(" Failed to fetch transaction Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error(" Betting API call failed due to network error: {}", e.getMessage());
        }
        return null;
    }


    public List<Bets> fetchBets() {
        Call<List<Bets>> call = apiClient.getBets();
        try {
            Response<List<Bets>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bets.", response.body().size());
                return response.body();
            } else {
                logger.error("Failed to fetch bets. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
        }
        return null;
    }

    public Double fetchBetCount() {
        Call<Double> call = apiClient.getBetsCount();
        try {
            Response<Double> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                double count = response.body();
                logger.info("Successfully fetched {} bet count.", count);
                return count;
            } else {
                logger.error("Failed to fetch betCounts. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error("API call  failed due to network error: {}", e.getMessage());
        }
        return 0.0;
    }

    public Double fetchPossibleWinSum() {
        Call<Double> call = apiClient.getPossibleWins();
        try {
            Response<Double> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                double count = response.body();
                logger.info("Successfully fetched {} possible wins summation.", count);
                return count;
            } else {
                logger.error("Failed to fetch possible wins summation . Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error("API  call  failed due to network error: {}", e.getMessage());
        }
        return 0.0;
    }

    public List<BetSlip> fetchBetSlipsByPhoneNumber(String authHeader, Long betId) {
        Call<List<BetSlip>> call = apiClient.getBetSlipsByUserAndBetId(authHeader, betId);

        try {
            Response<List<BetSlip>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bet slips.", response.body().size());
                return response.body();
            } else {
                logger.error("Failed to fetch bet slips. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
        }

        return Collections.emptyList();
    }

    public List<GamesDto> fetchAllGames() {

        Call<List<GamesDto>> call = apiClient.getAllGames();
        try {
            Response<List<GamesDto>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} games.", response.body().size());
                return response.body();
            } else {
                logger.error("Failed to fetch the Games. Response code: {}, Error: {}",
                        response.code(), response.errorBody());

            }

        } catch (IOException e) {

            logger.error("API call failed due to network error: {}", e.getMessage());

        }

return Collections.emptyList();


    }
    public PageResponse<User> fetchAllUsers(int page, int size) {
        Call<PageResponse<User>> call = apiClient.getAllUsers(page, size);

        try {
            Response<PageResponse<User>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} users.", response.body().getTotalElements());
                return response.body();
            } else {
                logger.error("Failed to fetch users. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody() != null ? response.errorBody().string() : "N/A");
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
        }

        return null;
    }
    public PageResponse<User> searchUsersByPhoneNumber(String phoneNumber, int page, int size) {
        Call<PageResponse<User>> call = apiClient.searchUsers(phoneNumber, page, size);

        try {
            Response<PageResponse<User>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} users for phone number: {}",
                        response.body().getTotalElements(), phoneNumber);
                return response.body();
            } else {
                logger.error("Failed to fetch users by PhoneNumber. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody() != null ? response.errorBody().string() : "N/A");
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
        }

        return null;
    }
    public User searchUserByPhoneNumber(String phoneNumber) {
        Call<User> call = apiClient.getUser(phoneNumber);

        try {
            Response<User> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched user with number {} ",
                   phoneNumber);
                logger.error("Suceessfully {}",response.body());
                return response.body();
            } else {
                logger.error("Failed to fetch user by PhoneNumber. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody() != null ? response.errorBody().string() : "N/A");
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
        }

        return null;
    }
    public List<Bets> searchBetsById(Long id) {
        Call<List<Bets>> call = apiClient.getBetsById(id);

        try {
            Response<List<Bets>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bets for Id: {}",
                        id);
                return response.body();
            } else {
                logger.error("Failed to fetch bets by Id. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody() != null ? response.errorBody().string() : "N/A");
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
        }

        return null;
    }
    public List<BetSlip> fetchBetSlipsByBetId(Long betId) {
        Call<List<BetSlip>> call = apiClient.getBetSlipsById(betId);

        try {
            Response<List<BetSlip>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bet slips.", response.body().size());
                return response.body();
            } else {
                logger.error("Failed to fetch betSlips. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
        }

        return Collections.emptyList();
    }





}



