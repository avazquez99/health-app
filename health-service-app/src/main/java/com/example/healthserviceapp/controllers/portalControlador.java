
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.myException.myException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class portalControlador {



    @GetMapping("/login")
    public String ingresar(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "usuario o contraseña invalidos");
        }
        return "login.html";
    }    
    
    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }
    
    @GetMapping("/registrar")
    public String registrarUsuario() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String pass,
            String pass2, ModelMap modelo) throws myException {

        try {

            usuarioService.registrar(email, pass, pass2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "inicio.html";
        } catch (myException e) {
            modelo.put("error", e.getMessage());           
            modelo.put("email", email);
            return "registro.html";
        }

    }
    
}
