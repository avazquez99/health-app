package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Rol;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.repository.ImagenRepository;
import com.example.healthserviceapp.repository.PacienteRepository;
import com.example.healthserviceapp.repository.PersonaRepository;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ramir
 */
@Service
public class PacienteService {

    @Autowired
    public PersonaRepository personaRepository;

    @Autowired
    public PacienteRepository pacienteRepository;
//    
//    @Autowired 
//    public ImagenService IS;
//

    @Transactional
    public void crearPaciente(ObraSocial obraSocial, Persona persona) throws MiException {

        verificarObraSocial(obraSocial);
        Paciente paciente = new Paciente();
        
            paciente.setId(persona.getId());
            paciente.setEmail(persona.getEmail());
            paciente.setPassword(persona.getPassword());
            paciente.setRol(persona.getRol());
            paciente.setActivo(persona.getActivo());
            paciente.setNombre(persona.getNombre());
            paciente.setApellido(persona.getApellido());
            paciente.setDni(persona.getDni());
            paciente.setDomilicio(persona.getDomilicio());
            paciente.setFechaNacimiento(persona.getFechaNacimiento());
            paciente.setSexo(persona.getSexo());
            paciente.setImagen(null);
            paciente.setObraSocial(obraSocial);
            pacienteRepository.save(paciente);
            eliminarPersona(persona);
    }

    @Transactional
    public void eliminarPersona(Persona persona){
         personaRepository.delete(persona);
    }
    
    @Transactional
    public void modificarPaciente(ObraSocial obraSocial, String id) throws MiException {
        verificarObraSocial(obraSocial);
        Optional<Paciente> presente = pacienteRepository.findById(id);
        Paciente paciente = new Paciente();
        if (presente.isPresent()) {
            paciente = presente.get();
            paciente.setObraSocial(obraSocial);
            pacienteRepository.save(paciente);
        }
    }

    @Transactional
    public void eliminarPaciente(String id) {

        Optional<Paciente> presente = pacienteRepository.findById(id);
        if (presente.isPresent()) {
            Paciente paciente = presente.get();
            paciente.setActivo(FALSE);
            pacienteRepository.save(paciente);
        }
    }

    private void verificarObraSocial(ObraSocial obraSocial) throws MiException {
        if (obraSocial == null) {
            throw new MiException("La obra social no puede estar vacío");
        }
    }
}