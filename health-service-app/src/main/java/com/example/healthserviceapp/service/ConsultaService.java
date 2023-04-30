package com.example.healthserviceapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.healthserviceapp.entity.Consulta;
import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.repository.ConsultaRepository;
import com.example.healthserviceapp.repository.PacienteRepository;
import com.example.healthserviceapp.repository.ProfesionalRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<String> listarConsultasPorProfesionalAgrupadoPorFecha(Profesional profesional, Long nTurnos) {
        return consultaRepository.listarConsultasPorProfesionalAgrupadoPorFecha(profesional, nTurnos);
    }

    @Transactional(readOnly = true)
    public List<Integer> listarHorarioPorProfesionalPorFecha(Profesional profesional, String fecha) {
        return consultaRepository.listarHorarioPorProfesionalPorFecha(profesional, fecha);
    }

    @Transactional
    public void crearConsulta(String idProfesional, String idPaciente, String fecha, Integer horario) {
        Optional<Profesional> respuestaProfesional = profesionalRepository.findById(idProfesional);
        Optional<Paciente> respuestaPaciente = pacienteRepository.findById(idPaciente);
        if (respuestaProfesional.isPresent() && respuestaPaciente.isPresent()) {
            Profesional profesional = respuestaProfesional.get();
            Paciente paciente = respuestaPaciente.get();
            Consulta consulta = new Consulta();
            consulta.setProfesional(profesional);
            consulta.setPaciente(paciente);
            consulta.setFecha(fecha);
            consulta.setHorario(horario);
            consulta.setDiagnostico(null);
            consultaRepository.save(consulta);
        }
    }

    @Transactional(readOnly = true)
    public List<Consulta> listarConsulta(String id) {
        return consultaRepository.listarConsulta(id);
    }

    @Transactional
    public void eliminar(String id) {
        Optional<Consulta> resp = consultaRepository.findById(id);
        Consulta consulta = new Consulta();
        if (resp.isPresent()) {
            consulta = resp.get();
            consultaRepository.delete(consulta);
        }
    }
}