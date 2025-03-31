package com.AdminBettingApi.AdminBetting.admin.services;
import com.AdminBettingApi.AdminBetting.admin.dto.AdminDto;
import com.AdminBettingApi.AdminBetting.admin.entity.Admin;
import com.AdminBettingApi.AdminBetting.admin.entity.Roles;
import com.AdminBettingApi.AdminBetting.admin.exceptions.UserNotFoundException;
import com.AdminBettingApi.AdminBetting.admin.repositories.AdminRepository;
import com.AdminBettingApi.AdminBetting.admin.repositories.RoleRepository;
import com.AdminBettingApi.AdminBetting.security.configuration.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class AdminService {

    private final AdminRepository adminRepository;
    private final JwtService jwtService;
private  final RoleRepository roleRepository;

    public AdminDto convertToDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminFirstName(admin.getAdminFirstName());
        adminDto.setAdminLastName(admin.getAdminLastName());
        adminDto.setAdminEmail(admin.getAdminEmail());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        adminDto.setRole(admin.getRoles().getName());
        adminDto.setAdminId(admin.getAdminId());
        return adminDto;
    }

    public AdminDto createAdmin(AdminDto adminDto, String authHeader) {
        String token = authHeader.substring(7);
        String adminPhoneNumber = jwtService.extractUserName(token);

        Admin existingAdmin = adminRepository.findByPhoneNumber(adminPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("Admin Not Found"));

        Roles role = roleRepository.findByName(adminDto.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + adminDto.getRole()));
        Admin newAdmin = new Admin();
        newAdmin.setAdminFirstName(adminDto.getAdminFirstName());
        newAdmin.setAdminLastName(adminDto.getAdminLastName());
        newAdmin.setAdminEmail(adminDto.getAdminEmail());
        newAdmin.setPhoneNumber(adminDto.getPhoneNumber());
        newAdmin.setRoles(role);



        Admin savedAdmin = adminRepository.save(newAdmin);
        return convertToDto(savedAdmin);
    }

    public AdminDto deleteAdmin(Long adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            adminRepository.deleteById(adminId);
            return convertToDto(admin);
        } else {
            throw new UserNotFoundException("Admin with Admin Id " + adminId + " not found.");
        }
    }

    public List<AdminDto> getAllAdmins() {
     List<Admin> admins = adminRepository.findAll();
     return admins.stream().map(this::convertToDto).toList();
    }
}
