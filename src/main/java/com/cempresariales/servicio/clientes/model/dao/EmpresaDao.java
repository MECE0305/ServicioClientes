package com.cempresariales.servicio.clientes.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.Cliente;
import com.cempresariales.servicio.commons.model.entity.Empresa;

public interface EmpresaDao extends JpaRepository<Empresa, Long>{

	
	List<Empresa> findByClienteIdCliente(Cliente clienteIdCliente);
	
}
