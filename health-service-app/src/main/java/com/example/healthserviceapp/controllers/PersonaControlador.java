
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.PersonaService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping("/datos")
    public String vistaDatosPerfil(ModelMap modelo){
        
        modelo.put("sexos", Sexo.values());   
        modelo.put("obrasSociales", ObraSocial.values());
        return "form.html";
    }
    
    @PostMapping("/actualizar")
    public String actualizarDatos(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String domicilio, @RequestParam Integer dni, @RequestParam Sexo sexo,
            @RequestParam Date fechaNacimiento, Imagen imagen) throws Exception{
        
        personaService.createPersona(dni, nombre, apellido, sexo, fechaNacimiento, imagen, domicilio);
        return "form.html";
    }
}
