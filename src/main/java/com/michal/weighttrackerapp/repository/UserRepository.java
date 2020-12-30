package com.michal.weighttrackerapp.repository;

import com.michal.weighttrackerapp.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
    UserAccount findByUsername(String userName);
}
