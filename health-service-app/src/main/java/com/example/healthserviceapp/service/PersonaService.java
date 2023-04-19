
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.enums.Sexo;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    
    
    public void createPersona(Integer dni, String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, Imagen imagen, String domicilio){
        
    }
    
    public void verificarNombre(String nombre) throws MiException{
        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            throw new MiException("Error, el nombre no puede estar vacio");
        }
    
}
    
    public void verificarApellido(String apellido) throws MiException{
   if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {
            throw new MiException("Error, el apellido no puede estar vacio");
        }
 }
    
    public void verificarDni(Integer dni)throws MiException{
        if (true) {
            
        }
}
    public void verificarSexo(Sexo sexo)throws MiException{
    
}
    public void verificarFechaNacimiento(Date fechaNacimiento)throws MiException{
    
}
    public void verificarImagen(Imagen imagen)throws MiException{
    
}
    public void verificarDomicilio()throws MiException{
    
}
    
}
