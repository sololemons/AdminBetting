package com.AdminBettingApi.AdminBetting.security.services;

import com.AdminBettingApi.AdminBetting.admin.entity.Admin;
import com.AdminBettingApi.AdminBetting.admin.exceptions.MissingFieldException;
import com.AdminBettingApi.AdminBetting.admin.exceptions.UserNotFoundException;
import com.AdminBettingApi.AdminBetting.admin.repositories.AdminRepository;
import com.AdminBettingApi.AdminBetting.security.configuration.JwtService;
import com.AdminBettingApi.AdminBetting.security.entity.AuthenticationRequest;
import com.AdminBettingApi.AdminBetting.security.entity.AuthenticationResponse;
import com.AdminBettingApi.AdminBetting.security.entity.RegisterRequest;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AdminRepository repository;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private final AdminRepository adminRepository;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // Validate phone number before authentication
        String formattedPhoneNumber = validateAndFormatPhoneNumber(request.getPhoneNumber().trim());
        String Password = request.getPassword().trim();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        formattedPhoneNumber,
                        Password

                )
        );
        var user = repository.findByPhoneNumber(formattedPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found with phone number: " + formattedPhoneNumber));
        var jwtToken = jwtService.generateAuthenticationToken(user);


        return AuthenticationResponse.builder().
                token(jwtToken)
                .role(user.getRoles().getName())
                .build();

    }

    public AuthenticationResponse register(RegisterRequest request) {

        String formattedPhoneNumber = validateAndFormatPhoneNumber(request.getPhoneNumber());


        String password = request.getPassword().trim();
        String confirmPassword = request.getConfirmPassword().trim();

        if (!password.equals(confirmPassword)) {
            throw new MissingFieldException("Passwords do not match!");
        }


        Optional<Admin> existingAdmin = adminRepository.findByPhoneNumber(formattedPhoneNumber);

        if (existingAdmin.isEmpty()) {
            throw new UserNotFoundException("Phone number not found. Contact the administrator to add your details.");
        }

        Admin admin = existingAdmin.get();


        if (admin.getAdminPassword() != null && !admin.getAdminPassword().isEmpty()) {
            throw new MissingFieldException("This phone number is already registered!");
        }


        admin.setAdminPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);


        var jwtToken = jwtService.generateRegistrationToken(admin);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    private String validateAndFormatPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, "KE");
            if (!phoneUtil.isValidNumber(number)) {
                throw new MissingFieldException("Invalid phone number!");
            }

            if (number.getCountryCode() != 254) {
                throw new MissingFieldException("Only Kenyan phone numbers (+254) are allowed!");
            }


            return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {
            throw new MissingFieldException("Invalid phone number format or it is null!");
        }
    }


}