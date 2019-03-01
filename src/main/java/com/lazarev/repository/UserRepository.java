package com.lazarev.repository;

import com.lazarev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByEmail(String email);
    User findByPhone(String phone);

    Optional<User> findByEmail(String username);
}
