package com.example.UserService.repositories;

import com.example.UserService.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String PhoneNumber);
    boolean existsByPhoneNumber(String PhoneNumber);
}
