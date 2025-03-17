package com.AdminBettingApi.AdminBetting.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="admin_id")
    private long adminId;
    @Column(name = "first_name")
    private String adminFirstName;
    @Column(name = "last_name")
    private String adminLastName;
    @Column(name = "email")
    private String adminEmail;
    @Column(name ="password")
    private String adminPassword;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Roles roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = roles.getPermissions().stream()
                .map(permission -> (GrantedAuthority) permission::getName)
                .collect(Collectors.toSet());

        authorities.add((GrantedAuthority) () -> "ROLE_" + roles.getName());

        return authorities;
    }


    @Override
    public String getPassword() {
        return  adminPassword;
    }


    @Override
    public String getUsername() {
        return  phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
      return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
