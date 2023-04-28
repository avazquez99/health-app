
package com.example.healthserviceapp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.service.ProfesionalService;
import com.example.healthserviceapp.service.UsuarioService;

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
public class ConsultaControlador {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping("/provincia")
    public String provincias(HttpSession session, ModelMap modelo){
        modelo.put("paso", 1);
        modelo.put("provincias", profesionalService.listarProvincias());
        return "consulta.html";
    }

    @GetMapping("/especialidad")
    public String especialidades(@RequestParam String provincia, HttpSession session, ModelMap modelo){
        modelo.put("paso", 2);
        modelo.put("provincia", provincia);
        modelo.put("especialidades", profesionalService.listarEspecialidadesPorProvincia(provincia));
        return "consulta.html";
    }

    @GetMapping("/profesional")
    public String profesionales(@RequestParam String provincia, @RequestParam String especialidad, HttpSession session, ModelMap modelo){
        modelo.put("paso", 3);
        modelo.put("provincia", provincia);
        modelo.put("especialidad", especialidad);
        modelo.put("profesionales", profesionalService.listarProfesionalPorEspecialidadesPorProvincia(provincia, especialidad));
        return "consulta.html";
    }

    @GetMapping("/disponibilidad")
    public String disponibilidadProfesional(@RequestParam String provincia, @RequestParam String especialidad, @RequestParam String profesional, HttpSession session, ModelMap modelo){
        modelo.put("paso", 4);
        modelo.put("provincia", provincia);
        modelo.put("especialidad", especialidad);
        Usuario usuario = usuarioService.getOne(profesional);
        modelo.put("profesional", usuario);
        return "consulta.html";
    }
}
