/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cempresariales.servicio.commons.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 *
 * @author ADM-DGIP
 */
@Entity
@Table(name = "sector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s")
    , @NamedQuery(name = "Sector.findByIdSector", query = "SELECT s FROM Sector s WHERE s.idSector = :idSector")
    , @NamedQuery(name = "Sector.findByActualizaSector", query = "SELECT s FROM Sector s WHERE s.actualizaSector = :actualizaSector")
    , @NamedQuery(name = "Sector.findByCreaSector", query = "SELECT s FROM Sector s WHERE s.creaSector = :creaSector")
    , @NamedQuery(name = "Sector.findByDescripcionSector", query = "SELECT s FROM Sector s WHERE s.descripcionSector = :descripcionSector")
    , @NamedQuery(name = "Sector.findByNombreSector", query = "SELECT s FROM Sector s WHERE s.nombreSector = :nombreSector")})
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sector")
    private Long idSector;
    @Column(name = "actualiza_sector")
    @Temporal(TemporalType.DATE)
    private Date actualizaSector;
    @Column(name = "crea_sector")
    @Temporal(TemporalType.DATE)
    private Date creaSector;
    @Column(name = "descripcion_sector")
    private String descripcionSector;
    @Column(name = "nombre_sector")
    private String nombreSector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectorIdSector")
    @JsonBackReference
    private List<Empresa> empresaList;

    public Sector() {
    }

    public Sector(Long idSector) {
        this.idSector = idSector;
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public Date getActualizaSector() {
        return actualizaSector;
    }

    public void setActualizaSector(Date actualizaSector) {
        this.actualizaSector = actualizaSector;
    }

    public Date getCreaSector() {
        return creaSector;
    }

    public void setCreaSector(Date creaSector) {
        this.creaSector = creaSector;
    }

    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSector != null ? idSector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.idSector == null && other.idSector != null) || (this.idSector != null && !this.idSector.equals(other.idSector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cempresariales.servicio.commons.model.entity.Sector[ idSector=" + idSector + " ]";
    }
    
}
