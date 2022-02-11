package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgenciaDao extends JpaRepository<Agencia, Long>{
	
	List<Agencia> findByEmpresaIdEmpresa(Empresa empresaIdEmpresa);


	@Query("select ag from Agencia ag INNER JOIN " +
			" ZonaEstructuralHasCiudad zhc ON ag.zonaCiudad.idZonaCiudad = zhc.idZonaCiudad" +
			" where zhc.zonaEstructuralIdZonaEstructural.idZonaEstructural = ?1 group by ag.idAgencia")
	List<Agencia> findByZonaEstructural(Long idZonaEs);

}
