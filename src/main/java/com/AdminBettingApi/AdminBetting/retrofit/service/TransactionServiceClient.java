package com.AdminBettingApi.AdminBetting.retrofit.service;

import com.AdminBettingApi.AdminBetting.admin.exceptions.MissingFieldException;
import com.AdminBettingApi.AdminBetting.retrofit.models.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Service
public class TransactionServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceClient.class);
    private final AdminApiClient apiClient;

    public TransactionServiceClient(RetrofitClient retrofitClient) {
        this.apiClient = retrofitClient.getClient().create(AdminApiClient.class);
    }


    public List<TransactionHistory> fetchAllTransactionHistory() {
        Call<List<TransactionHistory>> call = apiClient.getAllTransactions();
        try {
            Response<List<TransactionHistory>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} transactions. Response: {}, Response code: {}",
                        response.body().size(), response.body(), response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch transactions. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch transactions. Response code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }
    }


    public List<Bets> fetchBets() {
        Call<List<Bets>> call = apiClient.getBets();
        try {
            Response<List<Bets>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bets. The Response : {} . With the response Code : {}", response.body().size(), response.body(), response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch bets. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch bets. Response code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }
    }


    public List<GamesDto> fetchAllGames() {

        Call<List<GamesDto>> call = apiClient.getAllGames();
        try {
            Response<List<GamesDto>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} games. The Response : {} . With the Response Code :{} ", response.body().size(), response.body(), response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch the Games. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch the Games. Response code: " + response.code());

            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }


    }

    public PageResponse<User> fetchAllUsers(int page, int size) {
        Call<PageResponse<User>> call = apiClient.getAllUsers(page, size);

        try {
            Response<PageResponse<User>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} users. The Received Parameter was: {} . The Response was :{} . Response Code:  {} ", response.body().getTotalElements(), page, response.body(), response.code());
                return response.body();

            } else {
                logger.error("Failed to fetch users. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch users. Response Code: " + response.code());
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public PageResponse<User> searchUsersByPhoneNumber(String phoneNumber, int page, int size) {
        Call<PageResponse<User>> call = apiClient.searchUsers(phoneNumber, page, size);

        try {
            Response<PageResponse<User>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} users for phone number: {} . With Response Code: {} . The response: {}",
                        response.body().getTotalElements(), phoneNumber, response.code(), response.body());
                return response.body();
            } else {
                logger.error("Failed to fetch users by PhoneNumber. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch users by PhoneNumber: Response Code: " + response.code());
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
            throw new MissingFieldException("API call failed due to network error");
        }
    }

    public User searchUserByPhoneNumber(String phoneNumber) {
        Call<User> call = apiClient.getUser(phoneNumber);

        try {
            Response<User> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched user with number {} . The Response was : {} . With Response Code : {} ",
                        phoneNumber, response.body(), response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch user by PhoneNumber. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch user by PhoneNumber: Response Code: " + response.code());
            }

        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<Bets> searchBetsById(Long id) {
        Call<List<Bets>> call = apiClient.getBetsById(id);

        try {
            Response<List<Bets>> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bets for Id: {}, The Bets : {}, With Response Code: {}",
                        response.body().size(), id, response.body(), response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch bets by Id. Response Code: {}, Error Body: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch bets by Id. Response Code: " + response.code());

            }


        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage(), e);
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<BetSlip> fetchBetSlipsByBetId(Long betId) {
        Call<List<BetSlip>> call = apiClient.getBetSlipsById(betId);

        try {
            Response<List<BetSlip>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} bet slips. The Received Param was: {} . With The Response Code {} . It Returned BetSlips : {} .", response.body().size(), betId, response.code(), response.body());
                return response.body();
            } else {
                logger.error("Failed to fetch betSlips. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch betSlips. Response Code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<TransactionHistory> fetchTransactionsById(Long id) {
        Call<List<TransactionHistory>> call = apiClient.getTransactionsByTransactionId(id);

        try {
            Response<List<TransactionHistory>> response = call.execute();
            String apiUrl = call.request().url().toString();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully fetched {} transactions. Received data: {} . It Fetched from ApiUrl: {} . With Code : {} ", response.body().size(), id, apiUrl, response.code());
                return response.body();
            } else {
                logger.error("Failed to fetch transactions. With Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to fetch transactions. Response Code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<TransactionHistory> searchTransactionsByTransactionRef(String transactionRef) {
        Call<List<TransactionHistory>> call = apiClient.getTransactionsByTransactionRef(transactionRef);

        try {
            Response<List<TransactionHistory>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully searched and fetched {} transactions. The Param Received was :{} . With Response Code {} .", response.body().size(), transactionRef, response.code());
                return response.body();
            } else {
                logger.error("Failed to search and fetch transactions. Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to search and fetch transactions. Response Code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<User> getUsers() {
        Call<List<User>> call = apiClient.getAllUsers();

        try {
            Response<List<User>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully  fetched {} users.  With Response Code {} .", response.body().size(), response.code());
                return response.body();
            } else {
                logger.error("Failed to search users . Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to search users. Response Code: " + response.code());

            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }

    }

    public List<BetSlip> getAllBetsSlips() {
        Call<List<BetSlip>> call = apiClient.getAllBetsSlips();

        try {
            Response<List<BetSlip>> response = call.execute();
            if (response.isSuccessful() && response.body() != null) {
                logger.info("Successfully  fetched {} betSlips.  With Response Code {} .", response.body().size(), response.code());
                return response.body();
            } else {
                logger.error("Failed to search BetSlips . Response code: {}, Error: {}",
                        response.code(), response.errorBody());
                throw new MissingFieldException("Failed to search BetSlips. Response Code: " + response.code());
            }
        } catch (IOException e) {
            logger.error("API call failed due to network error: {}", e.getMessage());
            throw new MissingFieldException("API call failed due to network error");
        }

    }


}



