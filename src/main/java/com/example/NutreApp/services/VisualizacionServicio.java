package com.example.NutreApp.services;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.NutreApp.models.Visualizacion;
import com.example.NutreApp.repositories.IVisualizacion;

@Service
public class VisualizacionServicio {

    //Para cocinar necesito las recetas
    //Para programar los servicios necesito los repositorios

    //INYECCION DE DEPENDENCIAS
    @Autowired
    private IVisualizacion repositorio;

    //metodo para guardar una visualizacion
    public Visualizacion guardar(Visualizacion datos){
        return this.repositorio.save(datos);

    }

}
