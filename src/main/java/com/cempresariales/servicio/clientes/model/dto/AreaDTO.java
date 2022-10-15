package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;
import java.util.List;

public class AreaDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nombreAgencia;
    private Long idRol;
    private String nombreRol;
    private Long idCategoria;
    private String nombreCategoria;
    private Double promedio;
    private Double peso;

    public AreaDTO() {
    }


    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
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

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public AreaDTO(String nombreAgencia, Long idRol, String nombreRol, Long idCategoria, String nombreCategoria, Double promedio, Double peso) {
        this.nombreAgencia = nombreAgencia;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.promedio = promedio;
        this.peso = peso;
    }

    public AreaDTO(String nombreCategoria, String nombreRol, String nombreAgencia, Float promedio) {

        this.nombreCategoria = nombreCategoria;
        this.nombreRol = nombreRol;
        this.nombreAgencia = nombreAgencia;
        this.promedio = promedio.doubleValue();

    }

    public AreaDTO(String nombreCategoria, String nombreRol, String nombreAgencia, Double promedio) {

        this.nombreCategoria = nombreCategoria;
        this.nombreRol = nombreRol;
        this.nombreAgencia = nombreAgencia;
        this.promedio = promedio;

    }
}
