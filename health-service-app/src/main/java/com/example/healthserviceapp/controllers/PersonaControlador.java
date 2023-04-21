
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import com.example.healthserviceapp.service.UsuarioService;

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

@Controller
@RequestMapping("/perfil")
public class PersonaControlador {
    
    @Autowired
    private PersonaService personaService;

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/datos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaDatosPerfil(ModelMap modelo){
        
        modelo.put("sexos", Sexo.values());   
        return "form.html";
    }
    
    @PostMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String actualizarDatos(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String domicilio,
            // , @RequestParam Sexo sexo,@RequestParam Date fechaNacimiento, @RequestParam Integer dni,
            HttpSession session) throws Exception{

        String id = session.getId();
        System.out.println(id);
        Usuario usuario = usuarioService.getOne(id);

        // SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        // Date fechaFormateada = formato.parse(fechaNacimiento);
        
        personaService.createPersona(usuario, nombre, apellido, domicilio);
        return "form.html";
    }
}
