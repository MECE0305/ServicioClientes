package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import com.cempresariales.servicio.commons.model.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.AgenciaDao;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class AgenciaServiceImp implements IAgenciaService{

	@Autowired
	private AgenciaDao agenciaDao;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Agencia> findAll() {	
		return agenciaDao.findAll();
	}

	@Override
	public Agencia findById(Long id) {
		return agenciaDao.findById(id).orElse(null);
	}

	@Override
	public Agencia save(Agencia agencia) {
		return agenciaDao.save(agencia);
	}

	@Override
	public void delete(Long id) {
		agenciaDao.deleteById(id);
	}

	@Override
	public List<Agencia> findByEmpresaIdEmpresa(Empresa empresa) {
		return agenciaDao.findByEmpresaIdEmpresa(empresa);
	}

}
