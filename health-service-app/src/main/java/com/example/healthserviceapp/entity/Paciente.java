package com.example.healthserviceapp.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.example.healthserviceapp.enums.ObraSocial;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public final class Paciente extends Persona{
    @Enumerated
    private ObraSocial obraSocial;
}
