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
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonaService {

    @Autowired
    private UsuarioRepository usuarioRep;

    @Autowired
    private PersonaRepository personaRep;

    @Autowired
    private ImagenService imagenService;

    @Transactional
    public void createPersona(String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, String domicilio, Integer dni,
            Usuario usuario, MultipartFile archivo) throws MiException {

        verificarNombre(nombre);
        verificarApellido(apellido);
        verificarDomicilio(domicilio);

        Persona persona = new Persona();

        persona.setId(usuario.getId());
        persona.setEmail(usuario.getEmail());
        persona.setPassword(usuario.getPassword());
        persona.setRol(usuario.getRol());
        persona.setActivo(usuario.getActivo());
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDni(dni);
        persona.setDomicilio(domicilio);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setSexo(sexo);
        Imagen imagen = imagenService.guardar(archivo);
        persona.setImagen(imagen);
        personaRep.save(persona);

        borrarUsuario(usuario);
    }

    @Transactional
    public void borrarUsuario(Usuario usuario) {
        usuarioRep.delete(usuario);
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

    @Transactional
    public void modifyPersona(Persona persona, String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, String domicilio, Integer dni,
            MultipartFile archivo) throws MiException {

        Optional<Persona> respuesta = personaRep.findById(persona.getId());

        if (respuesta.isPresent()) {
            Persona nueva_persona = respuesta.get();
            nueva_persona.setNombre(nombre);
            nueva_persona.setApellido(apellido);
            nueva_persona.setSexo(sexo);
            nueva_persona.setDomicilio(domicilio);
            nueva_persona.setFechaNacimiento(fechaNacimiento);
            nueva_persona.setDni(dni);
            String id_imagen = nueva_persona.getImagen().getId();
            Imagen imagen = imagenService.actualizar(id_imagen, archivo);
            nueva_persona.setImagen(imagen);
            personaRep.save(nueva_persona);
        }
    }
}
