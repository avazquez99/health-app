package com.example.healthserviceapp.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.example.healthserviceapp.enums.ObraSocial;
import javax.persistence.EnumType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public final class Paciente extends Persona{
    @Enumerated(EnumType.STRING)
    private ObraSocial obraSocial;
}
