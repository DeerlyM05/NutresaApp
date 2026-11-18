package com.example.NutreApp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreApp.models.Usuario;
import com.example.NutreApp.models.Video;
import com.example.NutreApp.repositories.IVideioRepositorio;

@Service
public class VideoServicio {

    //Para cocinar necesito las recetas
    //Para programar los servicios necesito los repositorios

    //INYECCION DE DEPENDENCIAS
    @Autowired
    private IVideioRepositorio repositorio;

    //metodo para guardar un usuario
    public Video guardar(Video datos){
        return this.repositorio.save(datos);

    }
}
