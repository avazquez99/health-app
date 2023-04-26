package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Disponibilidad;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Especialidad;
import com.example.healthserviceapp.enums.Provincias;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.ProfesionalService;
import com.example.healthserviceapp.service.UsuarioService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profesional")
public class ProfesionalControlador {

    @Autowired
    private ProfesionalService profesionalServicio;
    
    @Autowired
    private UsuarioService usuarioServicio;

    @GetMapping("/registrar")
    public String registrarProfesional(ModelMap modelo) {

        modelo.put("especialidades", Especialidad.values());
        modelo.put("provincias", Provincias.values());

        return "profesional_form.html";

    }

    @GetMapping("/especialidades")
    public String listaEspecialidades(ModelMap modelo) throws MiException {

        List<Profesional> profesionales = profesionalServicio.listarProfesionales();
        modelo.addAttribute("profesionales", profesionales);

        //modelo.put("exito", "La lista de profesionales se muestra a continuación");

        modelo.put("especialidades", Especialidad.values());


        return "especialidades.html";

    }
    
    @GetMapping("/turno")
    public String reservarTurno(ModelMap modelo) throws MiException {
        
         List<Profesional> profesionales = profesionalServicio.listarProfesionales();
        modelo.addAttribute("profesionales", profesionales);

        
        modelo.put("especialidades", Especialidad.values());

        return "turno.html";
    }

    @PostMapping("/registro")
    public String registroProfesional(@RequestParam String id, String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, String domicilio, Integer dni, MultipartFile archivo,
            Provincias provincia, String matricula, Especialidad especialidad, Disponibilidad disponibilidad,
            ModelMap modelo, HttpSession session) throws MiException {  ///FALTA LA DISPONIBILIDAD


        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date dataFormateada = formato.parse(fechaNacimiento);

        if (session.getAttribute("usuariosession") instanceof Profesional) {
            
            Profesional profesional = (Profesional) session.getAttribute("usuariosession");
            profesionalServicio.modificarProfesional(profesional, nombre, apellido, sexo, dataFormateada, domicilio, dni, imagen, obraSocial);
            usuarioServicio.loadUserByUsername(profesional.getEmail());
            
        } else if (session.getAttribute("usuariosession") instanceof Usuario) {
            
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            profesionalServicio.crearProfesional(nombre, apellido, sexo, dataFormateada, domicilio, dni, usuario, imagen, obraSocial);
            usuarioServicio.loadUserByUsername(usuario.getEmail());
            
        }
        
        return "redirect:/";
    }

    }

}
