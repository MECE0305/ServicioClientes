package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;

public interface IAgenciaService {
	public List<Agencia> findAll();
	public Agencia findById(Long id);
	public Agencia save(Agencia categoria);
	public void delete(Long id);
	
	public List<Agencia> findByEmpresaIdEmpresa(Empresa empresa);
}
