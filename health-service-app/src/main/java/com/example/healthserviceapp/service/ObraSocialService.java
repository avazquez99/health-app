package com.example.healthserviceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthserviceapp.entity.ObraSocial;
import com.example.healthserviceapp.repository.ObraSocialRepository;

@Service
public class ObraSocialService {
    @Autowired
    private ObraSocialRepository obraSocialRepository;

    public ObraSocial buscarPorId(String id) {
        Optional<ObraSocial> obraSocial = obraSocialRepository.findById(id);
        if (obraSocial.isPresent()){
            return obraSocial.get();
        }
        return null;
    }

    public ObraSocial buscarPorNombre(String nombre) {
        Optional<ObraSocial> obraSocial = obraSocialRepository.buscarPorNombre(nombre);
        if (obraSocial.isPresent()){
            return obraSocial.get();
        }
        return null;
    }

    public List<String> listarNombresObrasSociales(){
        return obraSocialRepository.listarNombresObrasSociales();
    }

    public void crearObraSocial(String nombre, Double precio){
        Optional<ObraSocial> obraSocial = obraSocialRepository.buscarPorNombre(nombre);
        
        if (!obraSocial.isPresent()){
            ObraSocial nuevaObraSocial = new ObraSocial(nombre, precio);
            obraSocialRepository.save(nuevaObraSocial);
        }
    }

    public List<ObraSocial> listarObrasSociales(){
        return obraSocialRepository.listarObrasSociales();
    }
}
