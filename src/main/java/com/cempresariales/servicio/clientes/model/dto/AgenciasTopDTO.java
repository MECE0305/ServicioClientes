package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class AgenciasTopDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idAgencia;
    private String nombreAgencia;
    private String promedioTotal;
    private String promedioSeccion;
    private String promedioPreguntas;

    public AgenciasTopDTO() {
    }

    public AgenciasTopDTO(Long idAgencia, String nombreAgencia, String promedioTotal, String promedioSeccion, String promedioPreguntas) {
        this.idAgencia = idAgencia;
        this.nombreAgencia = nombreAgencia;
        this.promedioTotal = promedioTotal;
        this.promedioSeccion = promedioSeccion;
        this.promedioPreguntas = promedioPreguntas;
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
