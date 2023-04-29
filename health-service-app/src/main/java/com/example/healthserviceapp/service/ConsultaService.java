package com.example.healthserviceapp.service;

import com.example.healthserviceapp.entity.Consulta;
import com.example.healthserviceapp.repository.ConsultaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRep;
    
   public Consulta listarConsulta(String id){
      return consultaRep.listarConsulta(id);
   }
    
   public void eliminar(String id){
       
       Optional<Consulta> resp = consultaRep.findById(id);
       Consulta consulta = new Consulta();
       if (resp.isPresent()) {
           consulta = resp.get();
           consultaRep.delete(consulta);
       }
       
   }
   
}
