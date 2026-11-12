package com.example.NutreApp.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreApp.models.Usuario;

@Repository 
public interface IUsuarioRepositorio extends JpaRepository<Usuario,UUID>{

    //Consultas pre-fabricadas
    //1.Buscar un usuario asociado a un departamento y a un rol especifico
    List<Usuario> findByDepartamentoAndRol(String departamento, String rol);

    Optional<Usuario> findByCorreo(String correo);

    List<Usuario> findByRolOrderByNombreAsc(String rol);

    boolean exiexistsByCorreo(String correo);


    }
