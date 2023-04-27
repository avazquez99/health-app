
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
import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.enums.Provincias;
import com.example.healthserviceapp.service.UsuarioService;

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
public class ConsultaControlador {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/provincia")
    public String provincias(HttpSession session, ModelMap modelo){
        modelo.put("paso", 1);
        modelo.put("provincias", Provincias.values());
        return "consulta.html";
    }

    @GetMapping("/especialidad")
    public String especialidades(@RequestParam String provincia, HttpSession session, ModelMap modelo){
        modelo.put("paso", 2);
        modelo.put("provincia", provincia);
        modelo.put("especialidades", Especialidad.values());
        return "consulta.html";
    }

    @GetMapping("/profesional")
    public String profesionales(@RequestParam String provincia, @RequestParam String especialidad, HttpSession session, ModelMap modelo){
        modelo.put("paso", 3);
        modelo.put("provincia", provincia);
        modelo.put("especialidad", especialidad);
        List<Usuario> profesionales = usuarioService.listarUsuarios();
        modelo.put("profesionales", profesionales);
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
