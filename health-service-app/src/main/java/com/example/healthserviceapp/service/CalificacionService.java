/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.entity.Calificacion;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.repository.ProfesionalRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramir
 */
@Service
public class CalificacionService {

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Transactional
    public void crearCalificacion(int nota, String profesionalId) {

        Profesional profesional = profesionalRepository.getById(profesionalId);

        Calificacion calificacion = new Calificacion();
        calificacion.setCalificacion(nota);
        calificacion.setProfesional(profesional);
        profesional.getCalificaciones().add(calificacion);
        profesionalRepository.save(profesional);

    }

    @Transactional
    public void promedioCalificacion(String profesionalId) {
        Profesional profesional = profesionalRepository.buscarPorId(profesionalId);
        List<Calificacion> calificaciones = profesional.getCalificaciones();
        int cantCalificaciones = calificaciones.size();
        int sumaCalificaciones = 0;
        for (Calificacion calificacion : calificaciones) {
            sumaCalificaciones += calificacion.getCalificacion();

        }
        if (cantCalificaciones > 0) {
            profesional.setCalificacion(sumaCalificaciones/cantCalificaciones);
            profesionalRepository.save(profesional);
            

        } else {
            profesional.setCalificacion(0);
        }

    }

}
