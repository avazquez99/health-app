/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.healthserviceapp.controllers;

import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.repository.ProfesionalRepository;
import com.example.healthserviceapp.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ramir
 */
@Controller
@RequestMapping("/calificar")
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;
    
    @Autowired
    private ProfesionalRepository profesionalRepository;
    
    
    
}
