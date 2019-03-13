package com.lazarev.repository;

import com.lazarev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String phone);
    //User getByUserId(Long id);
    Optional<User> findByEmail(String username);

//    boolean existsByEmailOrPhone(String email,String phone);
    boolean existsUserByEmailOrPhone(String email,String phone);

    List<User> findAllByEmailOrPhoneOrName(String email, String phone, String name);
}
