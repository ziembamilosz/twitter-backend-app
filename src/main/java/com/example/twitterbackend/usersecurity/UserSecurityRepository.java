package com.example.twitterbackend.usersecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Integer> {
    Optional<UserSecurity> findByEmail(String email);
    public boolean existsByEmail(String email);
}
