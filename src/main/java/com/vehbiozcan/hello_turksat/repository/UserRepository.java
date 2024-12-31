package com.vehbiozcan.hello_turksat.repository;

import com.vehbiozcan.hello_turksat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
