package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.ClienteDao;
import com.cempresariales.servicio.commons.model.entity.Cliente;

@Service
public class ClienteServiceImp implements IClienteService{

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteDao.deleteById(id);
	}

}
