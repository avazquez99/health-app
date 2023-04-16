/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.Exceptions.MiException;
import com.example.healthserviceapp.entity.Usuario;
import static com.example.healthserviceapp.enums.Rol.PACIENTE;
import com.example.healthserviceapp.repository.UsuarioRepository;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramir
 */
@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Transactional
    public void guardarUsuario(String email, String password, String password2) throws MiException {

        verificarEmail(email);
        verificarPassword(password, password2);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setActivo(TRUE);
        usuario.setRol(PACIENTE);

        usuarioRepository.save(usuario);

    }

    @Transactional
    public void modificarUsuario(String email, String password, String password2, String id) throws MiException {

        verificarPassword(password, password2);

        Optional<Usuario> presente = usuarioRepository.findById(id);
        if (presente.isPresent()) {

            Usuario usuario = presente.get();
            usuario.setPassword(password);

            usuarioRepository.save(usuario);

        }

    }
    
    @Transactional
    public void eliminarUsuario(String email){
        
        Optional<Usuario> presente = usuarioRepository.buscarPorEmail(email);
        if (presente.isPresent()) {
            Usuario usuario = presente.get();
            usuario.setActivo(FALSE);
            
        }
        
    }
    
    public Usuario getOne(String id){
        return usuarioRepository.getById(id);
    }

    public void verificarEmail(String email) throws MiException {
        if (email.isEmpty()) {
            throw new MiException("El email no puede estar vacío");
        }
        
        Optional<Usuario> presente = usuarioRepository.buscarPorEmail(email);
        
        if (presente.isPresent()) {
            throw new MiException("El email ya existe");
        }

    }

    public void verificarPassword(String password, String password2) throws MiException {

        if (password.isEmpty()) {
            throw new MiException("La constraseña no puede estar vacía");
        }
        if (password.trim().isEmpty()) {
            throw new MiException("La contraseña no puede estar vacía");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas no coinciden");
        }

    }
}
