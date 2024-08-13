package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
    
}
