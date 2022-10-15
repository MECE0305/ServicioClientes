package com.cempresariales.servicio.clientes.model.dto;

import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EmpleadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idEmpleado;
    private Boolean activoEmpleado;
    private String apellidoEmpleado;
    private String ciEmpleado;
    private Date creaEmpleado;
    private String fotoEmpleado;
    private String generoEmpleado;
    private String mailEmpleado;
    private String nombreEmpleado;
    private String telefonoEmpleado;
    private Long idAgencia;
    private String nombreAgencia;
    private String roles;

    public EmpleadoDTO() {

    }


    public EmpleadoDTO(Long idEmpleado, Boolean activoEmpleado, String apellidoEmpleado, String ciEmpleado, Date creaEmpleado, String fotoEmpleado, String generoEmpleado, String mailEmpleado, String nombreEmpleado, String telefonoEmpleado, Long idAgencia, String nombreAgencia, String roles) {
        this.idEmpleado = idEmpleado;
        this.activoEmpleado = activoEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.ciEmpleado = ciEmpleado;
        this.creaEmpleado = creaEmpleado;
        this.fotoEmpleado = fotoEmpleado;
        this.generoEmpleado = generoEmpleado;
        this.mailEmpleado = mailEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.idAgencia = idAgencia;
        this.nombreAgencia = nombreAgencia;
        this.roles = roles;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Boolean getActivoEmpleado() {
        return activoEmpleado;
    }

    public void setActivoEmpleado(Boolean activoEmpleado) {
        this.activoEmpleado = activoEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getCiEmpleado() {
        return ciEmpleado;
    }

    public void setCiEmpleado(String ciEmpleado) {
        this.ciEmpleado = ciEmpleado;
    }

    public Date getCreaEmpleado() {
        return creaEmpleado;
    }

    public void setCreaEmpleado(Date creaEmpleado) {
        this.creaEmpleado = creaEmpleado;
    }

    public String getFotoEmpleado() {
        return fotoEmpleado;
    }

    public void setFotoEmpleado(String fotoEmpleado) {
        this.fotoEmpleado = fotoEmpleado;
    }

    public String getGeneroEmpleado() {
        return generoEmpleado;
    }

    public void setGeneroEmpleado(String generoEmpleado) {
        this.generoEmpleado = generoEmpleado;
    }

    public String getMailEmpleado() {
        return mailEmpleado;
    }

    public void setMailEmpleado(String mailEmpleado) {
        this.mailEmpleado = mailEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
