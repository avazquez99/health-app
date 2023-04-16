package com.example.healthserviceapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.healthserviceapp.enums.Sexo;
import javax.persistence.EnumType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Persona extends Usuario{
    private Integer dni;

    private String nombre;
    private String apellido;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToOne
    private Imagen imagen;

    private String domilicio;
}
