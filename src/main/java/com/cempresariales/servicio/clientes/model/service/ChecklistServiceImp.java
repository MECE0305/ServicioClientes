package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.ChecklistDao;
import com.cempresariales.servicio.commons.model.entity.Categoria;
import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.Pregunta;
import com.cempresariales.servicio.commons.model.entity.Rol;

@Service
public class ChecklistServiceImp implements ChecklistService {

	@Autowired
	private ChecklistDao repo;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Checklist> findAll() {
		return (List<Checklist>) repo.findAll();
	}

	@Override
	public Checklist findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Checklist save(Checklist entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Checklist> findByRolIdRol(Rol id) {
		return repo.findByRolIdRol(id);
	}

	@Override
	public List<Pregunta> countPreguntasByChecklist(Long id) {
		return repo.countPreguntasByChecklist(id);
	}

	@Override
	public List<Categoria> countCategoriaByChecklist(Long id) {
		return repo.countCategoriaByChecklist(id);
	}

	@Override
	public List<Checklist> findCheckListByRoles(Collection<Long> expresion) {
		try {

			Iterator<Long> iterator = expresion.iterator();
			String cadena = "";
			int x = 0;
			while (iterator.hasNext()) {

				cadena += iterator.next() + ",";
				if (x == expresion.size() - 1) {
					cadena = cadena.substring(0, cadena.length() - 1);
				}

				x++;
			}

			StringBuilder queryString = new StringBuilder(
					"select cl from Checklist cl where cl.rolIdRol.idRol in " + "(" + cadena + ") ");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
