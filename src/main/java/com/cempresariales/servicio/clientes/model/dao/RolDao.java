package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cempresariales.servicio.commons.model.entity.Rol;

public interface RolDao extends JpaRepository<Rol, Long> {

	@Query("select r from Rol r where r.areaIdArea.idArea = ?1 ")
	public List<Rol> findRolByArea(@Param("idArea") Long idArea);




}
