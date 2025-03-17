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
}
