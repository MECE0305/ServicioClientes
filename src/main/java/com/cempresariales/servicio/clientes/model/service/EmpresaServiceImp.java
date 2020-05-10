package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.EmpresaDao;
import com.cempresariales.servicio.commons.model.entity.Empresa;

@Service
public class EmpresaServiceImp implements IEmpresaService {

	@Autowired
	private EmpresaDao empresaDao;

	@Autowired
	EntityManagerFactory emf;

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

	@Override
	public List<Empresa> empresaAll(Long idCliente) {
		EntityManager entityManager = emf.createEntityManager();
		List<Empresa> lista = entityManager.createQuery("SELECT e.nombre , r.nombre FROM Empresa e JOIN e.listaRegiones r  ", Empresa.class).getResultList();
		
		
		List<Empresa> list = new ArrayList<>();
		Empresa empresa = new Empresa();
		empresa.setNombreEmpresa("prueba");
		list.add(empresa);
		
		return list;
	}

}
