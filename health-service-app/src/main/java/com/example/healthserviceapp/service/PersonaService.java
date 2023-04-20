
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.repository.PersonaRepository;
import com.example.healthserviceapp.repository.UsuarioRepository;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    
    @Autowired
    private UsuarioRepository usuarioRep;
    
    @Autowired
    private PersonaRepository personaRep;
    
    @Transactional
    public void createPersona(Integer dni, String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, Imagen imagen, String domicilio)throws MiException, Exception{
             
        verificarNombre(nombre);
        verificarApellido(apellido);
        verificarDni(dni);
        verificarDomicilio(domicilio);
        verificarFechaNacimiento(fechaNacimiento);
        verificarSexo(sexo);
        
        Persona persona = new Persona();
        
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDni(dni);
        persona.setDomilicio(domicilio);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setSexo(sexo);
        persona.setImagen(imagen);
        personaRep.save(persona);
        
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
    public void verificarDni(Integer dni)throws MiException, Exception{
        if (dni == null || dni.toString().isEmpty() || dni.toString().trim().isEmpty()) {
          throw new MiException("Error, el dni no puede estar vacio");  
        } else {
            throw new Exception("Error al cargar el dato");
        }
}   
    public void verificarFechaNacimiento(Date fechaNacimiento)throws MiException, Exception{
     if (fechaNacimiento == null || fechaNacimiento.toString().isEmpty() || fechaNacimiento.toString().trim().isEmpty()) {
          throw new MiException("Error, el dni no puede estar vacio");  
        } else {
            throw new Exception("Error al cargar el dato");
        }
}
    public void verificarDomicilio(String domicilio)throws MiException{
    if (domicilio == null || domicilio.isEmpty() || domicilio.trim().isEmpty()) {
            throw new MiException("Error, el domicilio no puede estar vacio");
        }
}
    public void verificarSexo(Sexo sexo) throws MiException{
        if (!(Sexo.values().equals(sexo))) {
            throw new MiException("Error, el sexo no es correcto");
        }
    }
}
