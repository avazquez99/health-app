package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import com.example.healthserviceapp.service.UsuarioService;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String vistaDatosPerfil(ModelMap modelo, HttpSession session) {

        modelo.put("sexos", Sexo.values());
        modelo.put("obrasSociales", ObraSocial.values());
        String tipo = "";
        
        if (session.getAttribute("usuariosession") instanceof Usuario) {
            tipo = "Usuario";           
        }
        if (session.getAttribute("usuariosession") instanceof Persona) {
            tipo = "Persona";
            Persona persona = (Persona) session.getAttribute("usuariosession");
            modelo.put("persona", persona);
        }
        modelo.put("tipo", tipo);
        return "form.html";
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String actualizarDatos(@RequestParam String nombre, @RequestParam String apellido, MultipartFile imagen,
            @RequestParam String domicilio, @RequestParam Integer dni, @RequestParam Sexo sexo,
            @RequestParam String fechaNacimiento, HttpSession session) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date dataFormateada = formato.parse(fechaNacimiento);

        if (session.getAttribute("usuariosession") instanceof Persona) {
            Persona persona = (Persona) session.getAttribute("usuariosession");
            personaService.modifyPersona(persona, nombre, apellido, sexo, dataFormateada, domicilio, dni, imagen);
            usuarioService.loadUserByUsername(persona.getEmail());
        }
        else if (session.getAttribute("usuariosession") instanceof Usuario) {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            personaService.createPersona(nombre, apellido, sexo, dataFormateada, domicilio, dni, usuario, imagen);
            usuarioService.loadUserByUsername(usuario.getEmail());
        }

        return "redirect:/";
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
