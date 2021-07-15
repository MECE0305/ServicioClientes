package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class EncabezadoReporteBloquesDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idChecklist, idEvaluacion;
    private String nombreZonaEstructural, nombreCiudad, nombreAgencia, nombreEmpleado, nombreRol, nombreArea;

    public EncabezadoReporteBloquesDTO(Long idChecklist, Long idEvaluacion, String nombreZonaEstructural, String nombreCiudad, String nombreAgencia, String nombreEmpleado, String nombreRol, String nombreArea) {
        this.idChecklist = idChecklist;
        this.idEvaluacion = idEvaluacion;
        this.nombreZonaEstructural = nombreZonaEstructural;
        this.nombreCiudad = nombreCiudad;
        this.nombreAgencia = nombreAgencia;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreRol = nombreRol;
        this.nombreArea = nombreArea;
    }

    public Long getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(Long idChecklist) {
        this.idChecklist = idChecklist;
    }

    public Long getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNombreZonaEstructural() {
        return nombreZonaEstructural;
    }

    public void setNombreZonaEstructural(String nombreZonaEstructural) {
        this.nombreZonaEstructural = nombreZonaEstructural;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
}
