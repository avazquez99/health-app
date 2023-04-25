package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Disponibilidad;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.enums.Provincias;
import com.example.healthserviceapp.service.ProfesionalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        modelo.put("provincias", Provincias.values());

        return "profesional_form.html";

    }

    @GetMapping("/especialidades")
    public String listaEspecialidades(ModelMap modelo) {

        return "especialidades.html";
    }

    @PostMapping("/registro")
    public String registroProfesional(@RequestParam String id, @RequestParam String matricula,
            @RequestParam Especialidad especialidad, @RequestParam Provincias provincia,
            ModelMap modelo) throws MiException {
        //@RequestParam Disponibilidad disponibilidad) throws MiException {

        profesionalServicio.crearProfesional(id, especialidad, matricula, provincia);
        modelo.put("exito", "Los datos fueron actualizados correctamente!");

        return "profesional_form.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificarProfesional(@PathVariable String id, String matricula,
            Especialidad especialidad, Provincias provincia, Disponibilidad disponibilidad) throws MiException {
        
        profesionalServicio.modificarProfesional(id, matricula, especialidad, disponibilidad, matricula, provincia);
        return "index.hmtl";

    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProfesional(@PathVariable String id) throws MiException {
        
        profesionalServicio.eliminarProfesional(id);
        return "index.hmtl";

    }

}
