package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/perfil")
public class PersonaControlador {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/datos")
    public String vistaDatosPerfil(ModelMap modelo, HttpSession sesion) {
        Usuario logued = (Usuario) sesion.getAttribute("usuariosesion");

        modelo.put("usuario", logued);
        modelo.put("sexos", Sexo.values());
        modelo.put("obrasSociales", ObraSocial.values());
        return "form.html";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarDatos(@PathVariable String id, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String domicilio, @RequestParam int dni, @RequestParam Sexo sexo,
            @RequestParam String fechaNacimiento) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormateada = formato.parse(fechaNacimiento);
        personaService.createPersona(nombre, apellido, sexo, dataFormateada, domicilio, Integer.valueOf(dni), id);
        return "form.html";
    }

}
