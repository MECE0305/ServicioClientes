package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;

public class DetalleReporteBloquesDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idCategoria;
    private String nombreCategoria;
    private Double promedioCategoria;
    private Long totalRespuestas;
    private Long totalCumplidas;

    public DetalleReporteBloquesDTO(String nombreCategoria, Long idCategoria, Double promedioCategoria, Long totalRespuestas, Long totalCumplidas) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.promedioCategoria = promedioCategoria;
        this.totalRespuestas = totalRespuestas;
        this.totalCumplidas = totalCumplidas;
    }

    public Long getTotalRespuestas() {
        return totalRespuestas;
    }

    public void setTotalRespuestas(Long totalRespuestas) {
        this.totalRespuestas = totalRespuestas;
    }

    public Long getTotalCumplidas() {
        return totalCumplidas;
    }

    public void setTotalCumplidas(Long totalCumplidas) {
        this.totalCumplidas = totalCumplidas;
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

    public Double getPromedioCategoria() {
        return promedioCategoria;
    }

    public void setPromedioCategoria(Double promedioCategoria) {
        this.promedioCategoria = promedioCategoria;
    }
}
