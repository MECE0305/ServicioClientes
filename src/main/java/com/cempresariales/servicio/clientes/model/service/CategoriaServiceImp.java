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

import com.cempresariales.servicio.clientes.model.dao.CategoriaDao;
import com.cempresariales.servicio.commons.model.entity.Categoria;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private CategoriaDao repo;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Categoria> findAll() {
		return (List<Categoria>) repo.findAll();
	}

	@Override
	public Categoria findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Categoria save(Categoria entidad) {
		return repo.save(entidad);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Categoria> findCategoriasByChecklist(Collection<Long> expresion) {
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

			
			StringBuilder queryString = new StringBuilder("select cat from Categoria cat where cat.idCategoria in "
					+ "(select cl.checklistHasCatalogoPreguntaPK.catalogoPreguntaCategoriaIdCategoria from ChecklistHasCatalogoPregunta cl where cl.checklistHasCatalogoPreguntaPK.checklistIdChecklist in "
					+ "(" + cadena + ")) group by cat.nombreCategoria order by cat.nombreCategoria asc");

			Query query = entityManager.createQuery(queryString.toString());
			

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
