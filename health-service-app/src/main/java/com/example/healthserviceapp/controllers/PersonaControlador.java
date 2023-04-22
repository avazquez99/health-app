package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/perfil")
public class PersonaControlador {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/datos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaDatosPerfil(ModelMap modelo, HttpSession session) {

        modelo.put("sexos", Sexo.values());
        modelo.put("obrasSociales", ObraSocial.values());
        String tipo = "Anonimo";
        if (session.getAttribute("usuariosession") instanceof Usuario) {
            tipo = "Usuario";
        }
        if (session.getAttribute("usuariosession") instanceof Persona) {
            tipo = "Persona";
        }
        modelo.put("tipo", tipo);
        return "form.html";
    }

    @GetMapping("/datos/modificar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaModificarPerfil(ModelMap modelo, HttpSession session) {

        Persona persona = (Persona) session.getAttribute("usuariosession");

        modelo.put("logued", persona);
        modelo.put("sexos", Sexo.values());
        modelo.put("obrasSociales", ObraSocial.values());
        return "form_modify.html";
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String actualizarDatos(@RequestParam String nombre, @RequestParam String apellido, MultipartFile imagen,
            @RequestParam String domicilio, @RequestParam Integer dni, @RequestParam Sexo sexo,
            @RequestParam String fechaNacimiento, HttpSession session) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date dataFormateada = formato.parse(fechaNacimiento);

        if (session.getAttribute("usuariosession") instanceof Usuario) {

            Usuario usuario = (Usuario) session.getAttribute("usuariosession");

            personaService.createPersona(nombre, apellido, sexo, dataFormateada, domicilio, dni, usuario, imagen);

        }
        if (session.getAttribute("usuariosession") instanceof Persona) {

            Persona persona = (Persona) session.getAttribute("usuariosession");

            personaService.modifyPersona(nombre, apellido, sexo, dataFormateada, domicilio, dni, persona, imagen);
        }

        return "redirect:/";
    }
}
