package com.example.healthserviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthserviceapp.entity.Consulta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, String>{
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :id")
    public Consulta listarConsulta(@Param("id") String id);
}
