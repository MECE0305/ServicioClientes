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

}
