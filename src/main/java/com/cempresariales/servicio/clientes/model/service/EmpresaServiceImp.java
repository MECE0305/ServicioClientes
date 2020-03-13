package com.cempresariales.servicio.clientes.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.EmpresaDao;
import com.cempresariales.servicio.commons.model.entity.Empresa;

@Service
public class EmpresaServiceImp implements IEmpresaService{

	@Autowired
	private EmpresaDao empresaDao;
	
	@Override
	public List<Empresa> findAll() {
		return (List<Empresa>) empresaDao.findAll();
	}

	@Override
	public Empresa findById(Long id) {
		return empresaDao.findById(id).orElse(null);
	}

	@Override
	public Empresa save(Empresa empresa) {
		return empresaDao.save(empresa);
	}

	@Override
	public void deleteById(Long id) {
		empresaDao.deleteById(id);
	}

}
