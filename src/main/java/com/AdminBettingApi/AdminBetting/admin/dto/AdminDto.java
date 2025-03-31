package com.AdminBettingApi.AdminBetting.admin.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String adminFirstName;
    private String adminLastName;
    private String adminEmail;
    private String phoneNumber;
    private String role;
    private Long adminId;
}
