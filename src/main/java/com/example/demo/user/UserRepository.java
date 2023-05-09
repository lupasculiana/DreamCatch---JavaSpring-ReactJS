package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

 //responsible for data access
public interface UserRepository
        extends JpaRepository<Login, Long> {
//SELECT * FROM user WHERE username = ?
    @Query("SELECT s FROM Login s WHERE s.id = ?1")
    Optional<Login> findLoginByUsername(String id);
}
