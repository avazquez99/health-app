/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.healthserviceapp.repository;

import com.example.healthserviceapp.entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramir
 */
@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, String> {
    
}
