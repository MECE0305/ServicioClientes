package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class RolDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nombreRol;
    private String nombreAgencia;
    private Long idAgencia;
    private Double promedio;

    public RolDTO() {
    }

    public RolDTO(String nombreRol, String nombreAgencia, Long idAgencia, Double promedio) {
        this.nombreRol = nombreRol;
        this.nombreAgencia = nombreAgencia;
        this.idAgencia = idAgencia;
        this.promedio = promedio;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
