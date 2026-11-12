package com.example.NutreApp.models;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "visualizacion")
public class Visualizacion {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="fecha_visualizacion", nullable=false, unique=false)
    private LocalDate fecha_vizualizacion;

     @Column (name="completado", nullable=false, unique=false)
    private Boolean completado;

    @ManyToOne
    @JoinColumn (name="id")
    @JsonManagedReference (value="usuario_visualizacion")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name="id")
    @JsonManagedReference (value="video_visualizacion")
    private Video video;

    public Visualizacion() {
    }

    public Visualizacion(Boolean completado, LocalDate fecha_vizualizacion, UUID id) {
        this.completado = completado;
        this.fecha_vizualizacion = fecha_vizualizacion;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getFecha_vizualizacion() {
        return fecha_vizualizacion;
    }

    public void setFecha_vizualizacion(LocalDate fecha_vizualizacion) {
        this.fecha_vizualizacion = fecha_vizualizacion;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }


}