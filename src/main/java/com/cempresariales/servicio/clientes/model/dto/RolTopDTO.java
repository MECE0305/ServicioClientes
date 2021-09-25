package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class RolTopDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idRol;
    private String nombreRol;
    private String promedioTotal;
    private String promedioSeccion;
    private String promedioPreguntas;

    public RolTopDTO() {
    }

    public RolTopDTO(Long idRol, String nombreRol, String promedioTotal, String promedioSeccion, String promedioPreguntas) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.promedioTotal = promedioTotal;
        this.promedioSeccion = promedioSeccion;
        this.promedioPreguntas = promedioPreguntas;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getPromedioTotal() {
        return promedioTotal;
    }

    public void setPromedioTotal(String promedioTotal) {
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
