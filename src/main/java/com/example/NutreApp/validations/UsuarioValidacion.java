package com.example.NutreApp.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreApp.models.Usuario;
import com.example.NutreApp.repositories.IUsuarioRepositorio;

@Component 
public class UsuarioValidacion {

    @Autowired
    private IUsuarioRepositorio repositorio;
    // ********VALIDACIONES DE FORMATO*******

    //1. Valiadar que un campo sea obligatorio

    public void valiadarTextoObligatorio (String valor, String campo){
        if (valor == null || valor.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El campo " + campo +" es obligatorio");
        }
    }

    //2. Validar la longitud de un nombre 
    //trim() elimina espacios " jua"/ "juan "
    public void validarNombre (String nombre){
        if (nombre.trim().length()<3 || nombre.trim().length()>100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El nombre debe de tener entre 3 y 100 caracteres");
        }
    }

    // 3. Expresiones regulares para validar que un correo si sea correcto
    // "^[\\w.+-]+@[\\w-]+\\.[\\w.]+$"
    //

    public void validarCorreo (String correo){
        if(!correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el correo no cumple con el formato esperado");

        }
    }

    //4. Validar contraseña

    public void validarContrasena (String contrasena){
        if (contrasena.length()<8){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La contarseña debe tener mas de 8 caracteres ");
        }
    }
    // **********VALIDACIONES DE NEGOCIO******************

    //1. validar que el correo de un usuario que se quiere registrar no exista en la BD

    public void validarUsuarioNuevo (Usuario datos){
        String correoEntregado=datos.getCorreo();
        if (this.repositorio.existsByCorreo(correoEntregado)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El correo ya se encuentra registrado en la BD");
        }
    }
}
