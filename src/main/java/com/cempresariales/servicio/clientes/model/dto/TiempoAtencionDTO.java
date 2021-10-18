package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class TiempoAtencionDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idEmpleado;
    private String nombreEmpleado;
    private Long idArea;
    private String nombreArea;
    private Integer tiempoAtencion;
    private Integer tiempoEspera;
    private Integer tiempoPermanencia;

    public TiempoAtencionDTO() {
    }

    public TiempoAtencionDTO(Long idEmpleado, String nombreEmpleado, Long idArea, String nombreArea, Integer tiempoAtencion, Integer tiempoEspera, Integer tiempoPermanencia) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
        this.tiempoAtencion = tiempoAtencion;
        this.tiempoEspera = tiempoEspera;
        this.tiempoPermanencia = tiempoPermanencia;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Integer getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(Integer tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public Integer getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(Integer tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public Integer getTiempoPermanencia() {
        return tiempoPermanencia;
    }

    public void setTiempoPermanencia(Integer tiempoPermanencia) {
        this.tiempoPermanencia = tiempoPermanencia;
    }
}
