package com.example.NutreApp.models;

import java.util.List;
import java.util.UUID;

import com.example.NutreApp.models.helpers.Categoria;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.OneToMany;

public class Video {

    private UUID id;
    private String titulo;
    private String descripcion;
    private String url;
    private Categoria categoria;

    @OneToMany (mappedBy="video")
    @JsonManagedReference (value="video_visualizacion")
    private List<Visualizacion> visualizacion;

    public Video() {
    }

    public Video(Categoria categoria, String descripcion, UUID id, String titulo, String url) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.id = id;
        this.titulo = titulo;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }



}
