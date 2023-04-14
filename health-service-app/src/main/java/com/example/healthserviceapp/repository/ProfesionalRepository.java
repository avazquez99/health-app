package com.example.healthserviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthserviceapp.entity.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, String>{
    
}
