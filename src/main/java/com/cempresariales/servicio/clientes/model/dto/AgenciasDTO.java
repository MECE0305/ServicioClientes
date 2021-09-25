package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class AgenciasDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idAgencia;
    private String nombreAgencia;
    private Double promedioTotal;
    private String nombreRol;

    public AgenciasDTO() {
    }

    public AgenciasDTO(Long idAgencia, String nombreAgencia, Double promedioTotal, String nombreRol) {
        this.idAgencia = idAgencia;
        this.nombreAgencia = nombreAgencia;
        this.promedioTotal = promedioTotal;
        this.nombreRol = nombreRol;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public Double getPromedioTotal() {
        return promedioTotal;
    }

    public void setPromedioTotal(Double promedioTotal) {
        this.promedioTotal = promedioTotal;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
