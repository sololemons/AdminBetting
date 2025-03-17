package com.AdminBettingApi.AdminBetting.security.configuration;

import com.AdminBettingApi.AdminBetting.admin.repositories.AdminRepository;
import com.AdminBettingApi.AdminBetting.retrofit.service.TransactionServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AdminRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            logger.info("Checking user authentication for: {}", username);
            return repository.findByPhoneNumber(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}