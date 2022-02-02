package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;
import java.util.Date;

public class RangoDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String nombreRango,colorRango;
    private Long cantidad;


    public RangoDTO() {
        super();
    }

    public RangoDTO(String nombreRango, String colorRango, Long cantidad) {
        super();
        this.nombreRango = nombreRango;
        this.colorRango = colorRango;
        this.cantidad = cantidad;
    }

    public String getNombreRango() {
        return nombreRango;
    }

    public void setNombreRango(String nombreRango) {
        this.nombreRango = nombreRango;
    }

    public String getColorRango() {
        return colorRango;
    }

    public void setColorRango(String colorRango) {
        this.colorRango = colorRango;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
