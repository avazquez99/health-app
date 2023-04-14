package com.example.healthserviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthserviceapp.entity.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, String>{
    
}
