package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Disponibilidad;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.repository.ProfesionalRepository;
import com.example.healthserviceapp.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalService {

    @Autowired
    ProfesionalRepository profesionalRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public void crearProfesional(String id, Especialidad especialidad, String matricula) {

        Profesional profesional = new Profesional();

        Optional<Usuario> respuestaProfesional = usuarioRepository.findById(id);

        if (respuestaProfesional.isPresent()) {

            profesional.setEspecialidad(especialidad);
            profesional.setMatricula(matricula);
            //profesional.setDisponibilidad(disponibilidad);

            profesionalRepository.save(profesional);

        }

    }

    public List<Profesional> listarProfesionales() {

        List<Profesional> profesionales = new ArrayList();

        profesionales = profesionalRepository.findAll();

        return profesionales;

    }

    public void modificarProfesional(String id, String idProfesional, Especialidad especialidad, Disponibilidad disponibilidad,
            String matricula) throws MiException {

        Optional<Profesional> respuestaProfesional = profesionalRepository.findById(idProfesional); //Duda, si lo busco por matricula o por id

        if (respuestaProfesional.isPresent()) {

            Profesional profesional = respuestaProfesional.get();

            profesional.setDisponibilidad(disponibilidad);

            profesionalRepository.save(profesional);

        }

    }

    private void validarMatricula(String matricula) throws MiException {

        if (matricula.isEmpty() || matricula == null) {

            throw new MiException("La matrícula no puede ser un valor vacío o nulo");

        }

    }

    //private void validarDisponibilidad(Disponibilidad disponibilidad) throws MiException {
    //}
    //private void validarEspecialidad(Especialidad especialidad) throws MiException {
    //}
}
