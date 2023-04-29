package com.example.healthserviceapp.service;

import com.example.healthserviceapp.entity.Consulta;
import com.example.healthserviceapp.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRep;
    
   public Consulta listarConsulta(String id){
      return consultaRep.listarConsulta(id);
   }
    
}
