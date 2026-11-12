package com.example.NutreApp.repositories;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreApp.models.Video;
import com.example.NutreApp.models.helpers.Categoria;

@Repository 
public interface IVideioRepositorio extends JpaRepository<Video, UUID> {

    // 1. Buscar videos por categoria
    List<Video> findByCategoria(Categoria categoria);

    // 2. Buscar videos por titulo (que ignore si esta en mayuscula o minuscula)
    List<Video> findByTituloIgnoreCase(String titulo);

    // 3. Buscar un video por descripcion (contenga pedazos)
    List<Video> findByDescripcionContaining(String descripcion);

    // 4. Buscar videos por URL
    Optional<Video> findByUrl(String url);

    // 5. Buscar videos por titulo y categoria
    List<Video> findByTituloAndCategoria(String titulo, Categoria categoria);

}
