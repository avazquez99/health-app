package com.example.healthserviceapp.initializer;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.ObraSocial;
import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Persona;
import com.example.healthserviceapp.enums.Rol;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.service.ImagenService;
import com.example.healthserviceapp.service.ObraSocialService;
import com.example.healthserviceapp.service.PacienteService;
import com.example.healthserviceapp.service.UsuarioService;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObraSocialService obraSocialService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ImagenService imagenService;

    public DataInitializer() {
    }

    @Override
    public void run(String... args) throws MiException {
        usuarioService.crearUsuario("admin@admin.com", "123456", Rol.ADMIN);

        obraSocialService.crearObraSocial("PAMI", 10d);
        obraSocialService.crearObraSocial("IOMA", 20d);
        obraSocialService.crearObraSocial("UOM", 30d);
        obraSocialService.crearObraSocial("OSECAC", 40d);
        obraSocialService.crearObraSocial("OSDE", 50d);

        usuarioService.crearUsuario("paciente@paciente.com", "123456", Rol.PACIENTE);

        crearPaciente();

        usuarioService.crearUsuario("profesional@profesional.com", "123456", Rol.PROFESIONAL);
    }

    private void crearPaciente() {
        String[] nombres = { "Juan", "Pedro", "Maria", "Ana", "Lucia", "David", "Pepe", "Elias", "Ezequias", "Walter",
                "Lorena", "Natasha" };
        String[] apellidos = { "Garcia", "Martinez", "Gonzalez", "Lopez", "Perez", "Sanchez", "Rodriguez", "Fernandez",
                "Gonzalez", "Martin", "Hernandez", "Diaz" };

        Paciente paciente = new Paciente();
        paciente.setActivo(true);
        String nombre = randomOfArray(nombres).toString();
        paciente.setNombre(nombre);
        String apellido = randomOfArray(apellidos).toString();
        paciente.setApellido(apellido);
        Integer dni = intRandom(10000000, 90000000);
        paciente.setDni(dni);
        paciente.setDomicilio(nombre+" "+apellido+" "+dni);
        paciente.setEmail(nombre+apellido+dni+"@prueba.com");
        Date fechaNacimiento = new Date();
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setImagen(imagenService.defaultImagen());
        paciente.setObraSocial(randomObraSocial());
        paciente.setPassword(new BCryptPasswordEncoder().encode("123456"));
        paciente.setRol(Rol.PACIENTE);
        paciente.setSexo(Sexo.values()[intRandom(0,2)]);
        pacienteService.crearPaciente(paciente);
    }

    private Object randomOfArray(Object[] array) {
        int randomIndex = ThreadLocalRandom.current().nextInt(array.length);
        return array[randomIndex];
    }

    private Integer intRandom(Integer min, Integer max){
        Random random = new Random();
        int numero = random.nextInt(max) + min;
        return numero;
    }

    private ObraSocial randomObraSocial(){
        List<ObraSocial> lista = obraSocialService.listarObrasSociales();
        int numero = intRandom(0, lista.size());
        return lista.get(numero);
    }
}
