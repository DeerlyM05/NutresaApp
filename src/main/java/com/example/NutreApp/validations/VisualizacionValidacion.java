package com.example.NutreApp.validations;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreApp.repositories.IUsuarioRepositorio;
import com.example.NutreApp.repositories.IVideioRepositorio;
@Component
public class VisualizacionValidacion {
    @Autowired
    private IUsuarioRepositorio repositorioUsuario;
    @Autowired
    private IVideioRepositorio repositorioVideo;

    // V1. La fecha de visualizacion es obligatoria.                     -> 400
    public void validarFecha (LocalDate fecha){
        if (fecha==null) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La fecha de visualizacion es obligatorio");  
        }
    }
    // V2. La fecha NO puede ser futura (nadie ve un video manana).      -> 400
    //     Pista: fecha.isAfter(LocalDate.now())
      public void validarFechaActual(LocalDate fecha) {
        if (fecha == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La fecha de visualizacion es obligatoria");
        }
    
        if (fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La fecha de la visualizacion no puede ser futura");
        }
      }
    // V3. El campo completado es obligatorio (true o false, no null).   -> 400
        public void validarCompletar (Boolean estaCompletado){
            if (estaCompletado==null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El campo completado es obligatorio");
            }
        }
    // V4. El usuario y el video deben EXISTIR en la BD.                 -> 404
    //     Pista: inyectar IUsuarioRepositorio e IVideoRepositorio y
    //     usar existsById(id)
        public void validarUsuarioVideo (UUID usuarioId, UUID videoId){
          
            if (usuarioId == null || !repositorioUsuario.existsById(usuarioId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El usuario no existe");
            }

            if (videoId == null || !repositorioVideo.existsById(videoId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El video no existe");
            }
}   
    
}
