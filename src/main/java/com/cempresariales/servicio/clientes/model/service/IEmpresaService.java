package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Cliente;
import com.cempresariales.servicio.commons.model.entity.Empresa;

public interface IEmpresaService {

	public List<Empresa> findAll();

	public Empresa findById(Long id);

	public Empresa save(Empresa empresa);

	public void deleteById(Long id);

	public List<Empresa> empresaAll(Long idCliente);

	public List<Empresa> findByClienteIdCliente(Cliente cliente);
}
