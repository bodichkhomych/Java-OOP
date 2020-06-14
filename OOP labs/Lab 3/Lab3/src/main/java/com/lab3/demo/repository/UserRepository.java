package com.lab3.demo.repository;

import com.lab3.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> finByEmail(String email);
}
