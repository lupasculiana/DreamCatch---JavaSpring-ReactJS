package com.example.demo.dream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//responsible for data access
public interface DreamRepository
        extends JpaRepository<Dream, Integer> {

}
