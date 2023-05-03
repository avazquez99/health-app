package com.example.healthserviceapp.initializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.enums.Rol;
import com.example.healthserviceapp.service.ObraSocialService;
import com.example.healthserviceapp.service.UsuarioService;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObraSocialService obraSocialService;

    public DataInitializer() {
    }

    @Override
    public void run(String... args) throws MiException {
        usuarioService.crearUsuario("admin@admin.com", "123456", Rol.ADMIN);
        usuarioService.crearUsuario("aaa@aaa.com", "123456", Rol.PACIENTE);

        obraSocialService.crearObraSocial("PAMI", 10d);
        obraSocialService.crearObraSocial("IOMA", 20d);
        obraSocialService.crearObraSocial("UOM", 30d);
        obraSocialService.crearObraSocial("OSECAC", 40d);
        obraSocialService.crearObraSocial("OSDE", 50d);
    }
}
