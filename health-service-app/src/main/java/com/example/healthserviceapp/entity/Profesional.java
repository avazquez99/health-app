package com.example.healthserviceapp.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.example.healthserviceapp.enums.Especialidad;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Profesional extends Persona{
    private String matricula;

    @Enumerated
    private Especialidad especialidad;

    @OneToOne
    private Disponibilidad disponibilidad;
}
