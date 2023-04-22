
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import com.example.healthserviceapp.service.UsuarioService;

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

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/datos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaDatosPerfil(ModelMap modelo, HttpSession session){
        if (session.getAttribute("usuariosession") instanceof Usuario){
            System.out.println("ES UN USUARIO");
        }
        if (session.getAttribute("usuariosession") instanceof Persona){
            System.out.println("ES UNA PERSONA");
        }
        // Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("sexos", Sexo.values());
        return "form.html";
    }
    
    @PostMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String actualizarDatos(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String domicilio, MultipartFile imagen,
            // , @RequestParam Sexo sexo,@RequestParam Date fechaNacimiento, @RequestParam Integer dni,
            HttpSession session) throws Exception{
    
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        // SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        // Date fechaFormateada = formato.parse(fechaNacimiento);

        personaService.createPersona(usuario, nombre, apellido, domicilio, imagen);
        return "form.html";
    }
}
