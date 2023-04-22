
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Usuario;
import com.example.healthserviceapp.enums.Rol;
import com.example.healthserviceapp.repository.UsuarioRepository;
import com.example.healthserviceapp.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioRepository usuarioRep;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String lista(ModelMap modelo){
        
        List<Usuario> usuario = new ArrayList<>();
        usuario = usuarioRep.buscarUsuarios();
        
        modelo.addAttribute("roles", Rol.values());
        modelo.addAttribute("usuarios", usuario);
        return "panel.html";
    }
    
    @GetMapping("/baja/{id}")    
    public String bajaUsuario(@PathVariable String id, ModelMap modelo){
        usuarioService.eliminarUsuario(id);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/buscar/{email}")
    public String buscarPorEmail(@PathVariable String email, ModelMap model){
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "panel.html";
    }
}
