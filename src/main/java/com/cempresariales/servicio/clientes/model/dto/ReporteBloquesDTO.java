package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;
import java.util.List;

public class ReporteBloquesDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private EncabezadoReporteBloquesDTO encabezado;

    private List<DetalleReporteBloquesDTO> detalle;

    public ReporteBloquesDTO() {

    }

    public ReporteBloquesDTO(EncabezadoReporteBloquesDTO encabezado, List<DetalleReporteBloquesDTO> detalle) {
        this.encabezado = encabezado;
        this.detalle = detalle;
    }

    public EncabezadoReporteBloquesDTO getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(EncabezadoReporteBloquesDTO encabezado) {
        this.encabezado = encabezado;
    }

    public List<DetalleReporteBloquesDTO> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleReporteBloquesDTO> detalle) {
        this.detalle = detalle;
    }
}
