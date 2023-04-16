/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.healthserviceapp.service;

import com.example.healthserviceapp.entity.Imagen;
import com.example.healthserviceapp.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ramir
 */
@Service
public class ImagenService {
    
//    @Autowired
//    private ImagenRepository IR;
//    
//    @Transactional
//    public Imagen guardarImagen(MultipartFile archivo){
//        
//        if (archivo != null) {
//            try{
//                Imagen imagen = new Imagen();
//                
//                imagen.setMime(archivo.getContentType());
//                imagen.setNombre(archivo.getName());
//                imagen.setContenido(archivo.getBytes());
//                
//                return IR.save(imagen);
//            }catch(Exception e){
//                
//            }
//                  
//            
//        }
//        return null;
//        
//    }
//    
//    @Transactional
//    Public Imagen actualizarImagen
//    
}
