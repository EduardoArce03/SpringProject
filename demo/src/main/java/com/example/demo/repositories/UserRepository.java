package com.example.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername (String username);

    Users findByEmail (String email);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.password = ?1 WHERE u.email = ?2")
    void updatePassword(String password, String email);
}
