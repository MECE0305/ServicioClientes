package com.cempresariales.servicio.clientes.model.dto;

import java.io.Serializable;
import java.util.Date;

public class MedicionDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idEvaluacion,idEstado,idEmpresa, idAgencia, idEmpleado;
    private String nombreEmpresa , nombreAgencia,nombreEmpleado, nombreRol,nombreRango,colorRango,
            observacionEvaluacion,imagenEmpresa, fotoEmpleado, emailAgencia, telefonoAgencia, direccionAgencia;
    private boolean activoAgencia;
    private Float puntajeEvaluacion;
    private Date horaInicioEvaluacion,horaFinEvaluacion,atencionEvaluacion,contactoEvaluacion, esperaEvaluacion;


    public MedicionDTO() {
        super();
    }

    public MedicionDTO(Long idEmpresa,Long idAgencia,Long idEmpleado,Long idEvaluacion, Long idEstado, String nombreEmpresa, String nombreAgencia, String nombreEmpleado,
                       String nombreRol, String nombreRango, String colorRango, String observacionEvaluacion,
                       Float puntajeEvaluacion, Date horaInicioEvaluacion, Date horaFinEvaluacion, Date atencionEvaluacion,
                       Date contactoEvaluacion, Date esperaEvaluacion, String imagenEmpresa, String fotoEmpleado,
                       String emailAgencia, String telefonoAgencia, boolean activoAgencia, String direccionAgencia) {
        super();
        this.idEvaluacion = idEvaluacion;
        this.idEstado = idEstado;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreAgencia = nombreAgencia;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreRol = nombreRol;
        this.nombreRango = nombreRango;
        this.colorRango = colorRango;
        this.observacionEvaluacion = observacionEvaluacion;
        this.puntajeEvaluacion = puntajeEvaluacion;
        this.horaInicioEvaluacion = horaInicioEvaluacion;
        this.horaFinEvaluacion = horaFinEvaluacion;
        this.atencionEvaluacion = atencionEvaluacion;
        this.contactoEvaluacion = contactoEvaluacion;
        this.esperaEvaluacion = esperaEvaluacion;
        this.imagenEmpresa = imagenEmpresa;
        this.fotoEmpleado = fotoEmpleado;
        this.emailAgencia = emailAgencia;
        this.telefonoAgencia = telefonoAgencia;
        this.activoAgencia = activoAgencia;
        this.direccionAgencia = direccionAgencia;
        this.idEmpresa = idEmpresa;
        this.idAgencia = idAgencia;
        this.idEmpleado = idEmpleado;
    }


    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    public void setDireccionAgencia(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    public String getEmailAgencia() {
        return emailAgencia;
    }

    public void setEmailAgencia(String emailAgencia) {
        this.emailAgencia = emailAgencia;
    }

    public String getTelefonoAgencia() {
        return telefonoAgencia;
    }

    public void setTelefonoAgencia(String telefonoAgencia) {
        this.telefonoAgencia = telefonoAgencia;
    }

    public boolean isActivoAgencia() {
        return activoAgencia;
    }

    public void setActivoAgencia(boolean activoAgencia) {
        this.activoAgencia = activoAgencia;
    }

    public String getImagenEmpresa() {
        return imagenEmpresa;
    }

    public void setImagenEmpresa(String imagenEmpresa) {
        this.imagenEmpresa = imagenEmpresa;
    }

    public String getFotoEmpleado() {
        return fotoEmpleado;
    }

    public void setFotoEmpleado(String fotoEmpleado) {
        this.fotoEmpleado = fotoEmpleado;
    }

    public Long getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Long idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    public String getObservacionEvaluacion() {
        return observacionEvaluacion;
    }

    public void setObservacionEvaluacion(String observacionEvaluacion) {
        this.observacionEvaluacion = observacionEvaluacion;
    }

    public Float getPuntajeEvaluacion() {
        return puntajeEvaluacion;
    }

    public void setPuntajeEvaluacion(Float puntajeEvaluacion) {
        this.puntajeEvaluacion = puntajeEvaluacion;
    }

    public Date getHoraInicioEvaluacion() {
        return horaInicioEvaluacion;
    }

    public void setHoraInicioEvaluacion(Date horaInicioEvaluacion) {
        this.horaInicioEvaluacion = horaInicioEvaluacion;
    }

    public Date getHoraFinEvaluacion() {
        return horaFinEvaluacion;
    }

    public void setHoraFinEvaluacion(Date horaFinEvaluacion) {
        this.horaFinEvaluacion = horaFinEvaluacion;
    }

    public Date getAtencionEvaluacion() {
        return atencionEvaluacion;
    }

    public void setAtencionEvaluacion(Date atencionEvaluacion) {
        this.atencionEvaluacion = atencionEvaluacion;
    }

    public Date getContactoEvaluacion() {
        return contactoEvaluacion;
    }

    public void setContactoEvaluacion(Date contactoEvaluacion) {
        this.contactoEvaluacion = contactoEvaluacion;
    }

    public Date getEsperaEvaluacion() {
        return esperaEvaluacion;
    }

    public void setEsperaEvaluacion(Date esperaEvaluacion) {
        this.esperaEvaluacion = esperaEvaluacion;
    }
}
