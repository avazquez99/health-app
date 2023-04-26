package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PacienteService;
import com.example.healthserviceapp.service.UsuarioService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/paciente")
public class PacienteControlador {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
    public String crearPaciente(@RequestParam String nombre, @RequestParam String apellido, MultipartFile imagen,
            @RequestParam String domicilio, @RequestParam Integer dni, @RequestParam Sexo sexo,
            @RequestParam String fechaNacimiento, @RequestParam ObraSocial obraSocial, HttpSession session) throws Exception {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date dataFormateada = formato.parse(fechaNacimiento);

        if (session.getAttribute("usuariosession") instanceof Paciente) {
            Paciente paciente = (Paciente) session.getAttribute("usuariosession");
            pacienteService.modifyPaciente(paciente, nombre, apellido, sexo, dataFormateada, domicilio, dni, imagen, obraSocial);
            usuarioService.loadUserByUsername(paciente.getEmail());
        } else if (session.getAttribute("usuariosession") instanceof Usuario) {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            pacienteService.createPaciente(nombre, apellido, sexo, dataFormateada, domicilio, dni, usuario, imagen, obraSocial);
            usuarioService.loadUserByUsername(usuario.getEmail());
        }
        return "redirect:/";
    }
    
}
