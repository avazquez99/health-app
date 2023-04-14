package com.example.healthserviceapp.repository;

import org.springframework.stereotype.Repository;

import com.example.healthserviceapp.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
