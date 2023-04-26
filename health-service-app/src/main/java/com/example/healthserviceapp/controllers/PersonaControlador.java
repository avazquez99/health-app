package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import com.example.healthserviceapp.service.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PersonaControlador {
   
    @GetMapping("/datos")
    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaDatosPerfil(ModelMap modelo, HttpSession session) {

        modelo.put("sexos", Sexo.values());
        modelo.put("obrasSociales", ObraSocial.values());
        String tipo = "";
        
        if (session.getAttribute("usuariosession") instanceof Usuario) {
            tipo = "Usuario";           
        }
        if (session.getAttribute("usuariosession") instanceof Paciente) {
            tipo = "Paciente";
            Paciente paciente = (Paciente) session.getAttribute("usuariosession");
            modelo.put("paciente", paciente);
        }
        modelo.put("tipo", tipo);
        return "form.html";
    }

    @GetMapping("/imagen")
    public ResponseEntity<byte[]> imagenUsuario (HttpSession session){        
       Persona persona = (Persona) session.getAttribute("usuariosession");
       byte[] imagen= persona.getImagen().getContenido();
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.IMAGE_JPEG);
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
}
