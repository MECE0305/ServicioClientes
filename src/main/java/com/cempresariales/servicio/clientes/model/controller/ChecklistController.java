package com.cempresariales.servicio.clientes.model.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.clientes.model.service.ChecklistServiceImp;
import com.cempresariales.servicio.commons.model.entity.Categoria;
import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.Pregunta;
import com.cempresariales.servicio.commons.model.entity.Rol;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "checklist")
public class ChecklistController {

	@Autowired
	private ChecklistServiceImp repo;

	@GetMapping("/listar")
	public List<Checklist> listar() {
		return repo.findAll();
	}

	@GetMapping("/ver/{id}")
	public Checklist ver(@PathVariable Long id) {
		return repo.findById(id);
	}
	
	@GetMapping("/countPreguntasByChecklist/{id}")
	public int countPreguntasByChecklist(@PathVariable Long id) {
		return repo.countPreguntasByChecklist(id).size();
	}
	
	
	@GetMapping("/countCategoriaByChecklist/{id}")
	public int countCategoriaByChecklist(@PathVariable Long id) {
		return repo.countCategoriaByChecklist(id).size();
	}
	
	
	@GetMapping("/listPreguntasByChecklist/{id}")
	public List<Pregunta> listPreguntasByChecklist(@PathVariable Long id) {
		return repo.countPreguntasByChecklist(id);
	}
	
	
	@GetMapping("/listCategoriaByChecklist/{id}")
	public List<Categoria> listCategoriaByChecklist(@PathVariable Long id) {
		return repo.countCategoriaByChecklist(id);
	}
	
	
	@PostMapping("/findCheckListByRoles")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Checklist> findCheckListByRoles(@RequestBody Collection<Long> expresion) {
		return repo.findCheckListByRoles(expresion);
	}
	
	

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Checklist crear(@RequestBody Checklist entidad) {
		return repo.save(entidad);
	}

	@PostMapping("/findByRolIdRol")
	public List<Checklist> findByRolIdRol(@RequestBody Rol id) {
		return repo.findByRolIdRol(id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		repo.delete(id);
	}
}
