package com.example.NutreApp.validations;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreApp.models.Video;
import com.example.NutreApp.repositories.IVideioRepositorio;

@Component
public class VideoValidacion {
    private IVideioRepositorio repositorio;
    // V1. Titulo obligatorio y entre 5 y 120 caracteres.               -> 400
    
    public void validarTitulo (String valor, String campo){
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El campo " + campo +" es obligatorio");
        }
        if (valor.trim().length()<5 || valor.trim().length()>120) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El " + campo +" debe tener entre 5 y 120 caracteres");
        }
    }
    // V2. La URL es obligatoria y debe empezar por "http://" o
    //     "https://".                                                    -> 400
    //     Pista: url.startsWith("https://")
    public void validarUrl(String valor, String campo){
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El campo " + campo +" es obligatorio");
        }
        if (!valor.matches( "^https?://\\S+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"la "+ campo + " no cumple con el formato esperado");
        }
    }
    // V3. La categoria es obligatoria (no puede llegar null).           -> 400
    public void validarCategoria(String valor, String campo){
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El campo " + campo +" es obligatorio");
        }
    }
    // V4. La descripcion es opcional, pero si viene no puede
    //     superar 500 caracteres.                                        -> 400

    public void validarDescripcion(String valor){
        if (valor.length()>500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La descripcion no puede superar mas de 500 caracteres");
        }
    }
    // V5. No pueden existir dos videos con la misma URL.                -> 409
    //     Al CREAR: usar existsByUrl(url)
    //     Al MODIFICAR: usar la consulta SQL 5 de abajo (igual que
    //     contarCorreoEnOtroUsuario en Usuario)
    public void validarVideoNuevo(Video url){
         String urlEntregada =url.getUrl();
         if (this.repositorio.findByUrl(urlEntregada).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"La url ya se encuentra registrada en la BD");
         }
    }

}
