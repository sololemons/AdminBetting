package com.AdminBettingApi.AdminBetting.admin.controllers;

import com.AdminBettingApi.AdminBetting.admin.dto.AdminDto;
import com.AdminBettingApi.AdminBetting.admin.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<AdminDto> addAdmin(@RequestBody AdminDto adminDto,
                                             @RequestHeader("Authorization") String authHeader) {
        AdminDto createdAdmin = adminService.createAdmin(adminDto, authHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<AdminDto> deleteAdmin(@RequestParam Long adminId) {
        return ResponseEntity.ok(adminService.deleteAdmin(adminId));

    }
    @GetMapping("/get/all")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }


}
