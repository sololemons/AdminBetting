package com.AdminBettingApi.AdminBetting.admin.repositories;

import com.AdminBettingApi.AdminBetting.admin.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long> {

 Optional<Admin> findByPhoneNumber(String phoneNumber);
}
