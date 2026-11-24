package com.example.NutreApp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreApp.models.Usuario;
import com.example.NutreApp.repositories.IUsuarioRepositorio;
import com.example.NutreApp.validations.UsuarioValidacion;

public class UsuarioServicio {

    //Metodo para guardar
    //Metodo para buscar en Id
    //Metodo para buscar TODOS
    //Metodo para modificar
    //Metodo para eliminar
    //Metodo para buscar por departamento y rol
    //Metodo para buscar por correo
    //Metodo para buscar por rol
    //Metodo para verificar correo

    //Para cocinar necesito las recetas
    //Para programar los servicios necesito los repositorios

    //INYECCION DE DEPENDENCIAS
    @Autowired 
    private UsuarioValidacion validador;
    @Autowired
    private IUsuarioRepositorio repositorio;

    //metodo para guardar un usuario
    public Usuario guardar(Usuario datos){
        this.validador.validarCorreo(datos.getCorreo());
        this.validador.validarUsuarioNuevo(datos);
        this.validador.validarNombre(datos.getNombre());
        this.validador.validarContrasena(datos.getContrasena());
        return this.repositorio.save(datos);
    }

    //Metodo para buscar por Id
    public Usuario buscarPorId(UUID id){
        Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID no encontrado");

        }else{
            return usuarioBuscado.get();
        }
    }

    //Metodo para buscar todo
    public List<Usuario> buscarTodo(){
        return this.repositorio.findAll();
    }

    //Metodo para modificar 
    public Usuario modificarUsuario(UUID id, Usuario datos){
        
        Optional<Usuario>usuarioBuscado = this.repositorio.findById(id);

        if (!usuarioBuscado.isPresent()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");

        } else{
            //1. precargar la info que existe actualmente en la base de datos
            Usuario usuarioExistente = usuarioBuscado.get();

            //2. Espacio para modificar las propiedades permitidas
            usuarioExistente.setNombre(datos.getNombre());
            usuarioExistente.setRol(datos.getRol()); 
            
            //3. Guardar la info modificada en la base de datos
            return this.repositorio.save(usuarioExistente);
        }
    }

    //Metodo para eliminar
    public boolean eliminarUsuario(UUID id){
        Optional<Usuario>usuarioBuscado = this.repositorio.findById(id);

        if (!usuarioBuscado.isPresent()){
         // El httpStatus genera la respuesta 200,300,400,500 se puede moificar segun se adapte a la respuesta esperada
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");

        } else{
            this.repositorio.deleteById(id);
            return true;
        }
    }

}
