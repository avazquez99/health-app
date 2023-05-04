package com.example.healthserviceapp.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.enums.Provincias;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Profesional extends Persona{
    
    private String matricula;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @OneToOne
    private Disponibilidad disponibilidad;
    
    @Enumerated(EnumType.STRING)
    private Provincias provincia;
    
    @OneToMany(mappedBy = "profesional", cascade =CascadeType.ALL)//Guarda las calificaciones del médico
    private List<Calificacion> calificaciones = new ArrayList<>();
    
    private double calificacion;
}
