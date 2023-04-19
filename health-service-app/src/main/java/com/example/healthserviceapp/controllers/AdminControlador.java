
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioRepository usuarioRep;
    
    @GetMapping("/dashboard")
    public String lista(@RequestParam String id, ModelMap modelo){
        
        List<Usuario> usuario = new ArrayList<>();
        usuario = usuarioRep.buscarUsuarios();
        
        modelo.addAttribute("usuarios", usuario);
        return "panel.html";
    }
    
}