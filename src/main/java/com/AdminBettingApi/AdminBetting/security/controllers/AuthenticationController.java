package com.AdminBettingApi.AdminBetting.security.controllers;
import com.AdminBettingApi.AdminBetting.security.entity.AuthenticationRequest;
import com.AdminBettingApi.AdminBetting.security.entity.AuthenticationResponse;
import com.AdminBettingApi.AdminBetting.security.entity.RegisterRequest;
import com.AdminBettingApi.AdminBetting.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final AuthenticationService service;

    //Register an User
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    //Authenticate An User
    @PostMapping("/authenticate/admin")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}