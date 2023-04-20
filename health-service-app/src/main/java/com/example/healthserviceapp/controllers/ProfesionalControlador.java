package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {

    @Autowired
    private ProfesionalService profesionalServicio;

    @GetMapping("/registrar")
    public String registrarProfesional(ModelMap modelo) {
        
        modelo.put("especialidades", Especialidad.values());   
        
        return "profesional_form.html";

    }

    @PostMapping("/registro")
    public String registroProfesional(@RequestParam String id, @RequestParam String matricula, @RequestParam Especialidad especialidad) throws MiException {
            //@RequestParam Disponibilidad disponibilidad) throws MiException {

        profesionalServicio.crearProfesional(id, especialidad, matricula);

        //System.out.println("matricula: " + matricula);
        //System.out.println("especialidad: " + especialidad);
        //System.out.println("disponibilidad: " + disponibilidad);

        return "profesional_form.html";

    }

}
