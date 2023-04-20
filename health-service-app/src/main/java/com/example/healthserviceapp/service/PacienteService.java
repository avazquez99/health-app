
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.enums.ObraSocial;
import com.example.healthserviceapp.enums.Rol;
import com.example.healthserviceapp.enums.Sexo;
import com.example.healthserviceapp.repository.ImagenRepository;
import com.example.healthserviceapp.repository.PacienteRepository;
import static java.lang.Boolean.TRUE;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ramir
 */
@Service
public class PacienteService {

//    @Autowired
//    public PacienteRepository PR;
//    
//    @Autowired 
//    public ImagenService IS;
//
//    @Transactional
//    public void crearPaciente(String email, String password, String password2,
//            Integer dni, String nombre, String apellido, Sexo sexo, Date fechaNacimiento,
//            MultipartFile archivo, String domicilio, ObraSocial obraSocial) throws MiException {
//
//        verificarEmail(email);
//        verificarPassword(password, password2);
//        verificarDni(dni);
//        verificarNombre(nombre);
//        verificarApellido(apellido);
//        verificarSexo(sexo);
//        verificarFecha(fechaNacimiento);
//        //verificar imagen
//        verificarDomicilio(domicilio);
//        verificarObraSocial(obraSocial);
//
//        Paciente paciente = new Paciente();
//        
//        
//        paciente.setEmail(email);
//        paciente.setPassword(password);
//        paciente.setDni(dni);
//        paciente.setNombre(nombre);
//        paciente.setApellido(apellido);
//        paciente.setDomilicio(domicilio);
//        paciente.setSexo(sexo);
//        paciente.setFechaNacimiento(fechaNacimiento);
//        paciente.setObraSocial(obraSocial);
//        
//        paciente.setRol(Rol.PACIENTE);
//        paciente.setActivo(TRUE);
//        
//        Imagen imagen = IS.guardarImagen(archivo);
//        paciente.setImagen(imagen);
//    
//        PR.save(paciente);
//
//    }
//
//    @Transactional
//    public void modificarPaciente(String email, String password, String password2,
//            Integer dni, String nombre, String apellido, Sexo sexo, Date fechaNacimiento,
//            MultipartFile archivo, String domicilio, ObraSocial obraSocial, String id) throws MiException {
//
//        verificarEmail(email);
//        verificarPassword(password, password2);
//        verificarDni(dni);
//        verificarNombre(nombre);
//        verificarApellido(apellido);
//        verificarSexo(sexo);
//        verificarFecha(fechaNacimiento);
//        //verificar imagen
//        verificarDomicilio(domicilio);
//        verificarObraSocial(obraSocial);
//
//        Optional<Paciente> respuesta = PR.findById(id);
//        if (respuesta.isPresent()) {
//            Paciente paciente = respuesta.get();
//            paciente.setEmail(email);
//            paciente.setPassword(password);
//            paciente.setDni(dni);
//            paciente.setNombre(nombre);
//            paciente.setApellido(apellido);
//            paciente.setDomilicio(domicilio);
//            paciente.setSexo(sexo);
//            paciente.setFechaNacimiento(fechaNacimiento);
//            paciente.setObraSocial(obraSocial);
//            
//            String idImagen = null;
//                          
//
//            paciente.setImagen(imagen);
//            PR.save(paciente);
//
//        }
//    }
//    
//    public Paciente getOne(String id){
//        return PR.getOne(id);
//    }
//    
//    public String imagenPresente (Paciente paciente){
//        if (paciente.getImagen() != null) {
//            String idImagen = paciente.getImagen().getId();
//            return idImagen;
//        }
//        return null;
//    }
//
//    public void verificarEmail(String email) throws MiException {
//        if (email.isEmpty()) {
//            throw new MiException("El email no puede estar vacío");
//        }
//    }
//
//    public void verificarPassword(String password, String password2) throws MiException {
//        if (password.isEmpty()) {
//            throw new MiException("La constraseña no puede estar vacía");
//        }
//        if (!password.equals(password2)) {
//            throw new MiException("Las contraseñas no coinciden");
//        }
//    }
//
//    public void verificarDni(Integer DNI) throws MiException {
//        if (DNI == null) {
//            throw new MiException("El DNI no puede estar vacío");
//        }
//    }
//
//    public void verificarNombre(String nombre) throws MiException {
//        if (nombre.isEmpty()) {
//            throw new MiException("El nombre no puede estar vacío");
//        }
//    }
//
//    public void verificarApellido(String apellido) throws MiException {
//        if (apellido.isEmpty()) {
//            throw new MiException("El apellido no puede estar vacío");
//        }
//    }
//
//    public void verificarDomicilio(String domicilio) throws MiException {
//        if (domicilio.isEmpty()) {
//            throw new MiException("El domicilio no puede estar vacío");
//        }
//    }
//
//    public void verificarObraSocial(ObraSocial obrasocial) throws MiException {
//        if (obrasocial == null) {
//            throw new MiException("La obra social no puede estar vacío");
//        }
//    }
//
//    public void verificarSexo(Sexo sexo) throws MiException {
//        if (sexo == null) {
//            throw new MiException("El sexo no puede estar vacío");
//        }
//    }
//
//    public void verificarFecha(Date fecha) throws MiException {
//        if (fecha == null) {
//            throw new MiException("La fecha de nacimiento no puede estar vacío");
//        }
//    }
}
