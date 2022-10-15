package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class CategoriaTopDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idCategoria;
    private String nombreCategoria;
    private Double promedioTotal;
    private String promedioSeccion;
    private String promedioPreguntas;

    public CategoriaTopDTO() {
    }

    public CategoriaTopDTO(Long idCategoria, String nombreCategoria, Double promedioTotal, String promedioSeccion, String promedioPreguntas) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.promedioTotal = promedioTotal;
        this.promedioSeccion = promedioSeccion;
        this.promedioPreguntas = promedioPreguntas;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Double getPromedioTotal() {
        return promedioTotal;
    }

    public void setPromedioTotal(Double promedioTotal) {
        this.promedioTotal = promedioTotal;
    }

    public String getPromedioSeccion() {
        return promedioSeccion;
    }

    public void setPromedioSeccion(String promedioSeccion) {
        this.promedioSeccion = promedioSeccion;
    }

    public String getPromedioPreguntas() {
        return promedioPreguntas;
    }

    public void setPromedioPreguntas(String promedioPreguntas) {
        this.promedioPreguntas = promedioPreguntas;
    }
}
