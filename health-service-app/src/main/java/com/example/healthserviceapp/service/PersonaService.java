package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.repository.PersonaRepository;
import com.example.healthserviceapp.repository.UsuarioRepository;
import java.util.Date;
import java.util.Optional;
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
    public void createPersona( String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, String domicilio,Integer dni,
            String id) throws MiException, Exception {

        verificarNombre(nombre);
        verificarApellido(apellido);
        verificarDomicilio(domicilio);

        Persona persona = new Persona();
        Optional<Usuario> resp = usuarioRep.findById(id);
        if (resp.isPresent()) {
            persona.setId(resp.get().getId());
            persona.setEmail(resp.get().getEmail());
            persona.setPassword(resp.get().getPassword());
            persona.setRol(resp.get().getRol());
            persona.setActivo(resp.get().getActivo());
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setDni(dni);
            persona.setDomilicio(domicilio);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setSexo(sexo);
//            persona.setImagen(null);
            personaRep.save(persona);
        }

    }

    public void verificarNombre(String nombre) throws MiException {
        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
            throw new MiException("Error, el nombre no puede estar vacio");
        }

    }
    public void verificarApellido(String apellido) throws MiException {
        if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {
            throw new MiException("Error, el apellido no puede estar vacio");
        }
    }
    public void verificarDomicilio(String domicilio) throws MiException {
        if (domicilio == null || domicilio.isEmpty() || domicilio.trim().isEmpty()) {
            throw new MiException("Error, el domicilio no puede estar vacio");
        }
    }
}
