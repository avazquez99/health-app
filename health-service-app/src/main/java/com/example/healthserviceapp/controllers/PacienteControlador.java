package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.service.PacienteService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paciente")
public class PacienteControlador {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/registro")
    public String registroPaciente(@RequestParam ObraSocial obraSocial,
            ModelMap model, HttpSession session) {
      
        try {
            if (session.getAttribute("usuariosession") instanceof Usuario){
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        }
        if (session.getAttribute("usuariosession") instanceof Persona){
            Persona persona = (Persona) session.getAttribute("usuariosession");
            pacienteService.crearPaciente(obraSocial, persona);
        }          
            model.put("Exito", "Paciente registrado correctamente");
            return "form.html";
        } catch (MiException e) {
            System.out.println(e.getMessage());
            model.put("error", e.getMessage());
            return "form.html";
        }

    }

    @PostMapping("/modificar/{id}")
    public String modificarPaciente(@PathVariable String id, ModelMap model, ObraSocial obraSocial) throws MiException {
        pacienteService.modificarPaciente(obraSocial, id);
        return "index.hmtl";

    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable String id, ModelMap model, ObraSocial obraSocial) throws MiException {
        pacienteService.eliminarPaciente(id);
        return "index.hmtl";

    }
}
