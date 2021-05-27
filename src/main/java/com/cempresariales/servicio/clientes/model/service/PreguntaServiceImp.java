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

import com.cempresariales.servicio.clientes.model.dao.PreguntaDao;
import com.cempresariales.servicio.commons.model.entity.Pregunta;

@Service
public class PreguntaServiceImp implements PreguntaService {

	@Autowired
	private PreguntaDao repo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Pregunta> findAll() {
		return (List<Pregunta>) repo.findAll();
	}

	@Override
	public Pregunta findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Pregunta save(Pregunta entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Pregunta> findPreguntasByCategorias(Collection<Long> expresion) {
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
					"select cp.pregunta from CatalogoPregunta cp where cp.categoria.idCategoria in " + "(" + cadena + ") ");

			Query query = entityManager.createQuery(queryString.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
