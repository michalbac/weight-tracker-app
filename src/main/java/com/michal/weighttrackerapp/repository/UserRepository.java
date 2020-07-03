package com.michal.weighttrackerapp.repository;

import com.michal.weighttrackerapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
