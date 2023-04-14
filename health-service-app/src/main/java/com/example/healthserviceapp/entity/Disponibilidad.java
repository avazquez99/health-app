package com.example.healthserviceapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Disponibilidad {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Temporal(TemporalType.TIME)
    private Date entrada;

    @Temporal(TemporalType.TIME)
    private Date salida;

    @Temporal(TemporalType.TIME)
    private Date inicioDescanso;

    @Temporal(TemporalType.TIME)
    private Date finDescanso;
}
