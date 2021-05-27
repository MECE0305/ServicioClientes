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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.commons.model.entity.Checklist;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacion;
import com.cempresariales.servicio.commons.model.entity.ChecklistHasEvaluacionPK;
import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import com.cempresariales.servicio.clientes.model.service.ChecklistHasEvaluacionServiceImp;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "checklistevaluacion")
public class ChecklistHasEvaluacionController {

	@Autowired
	private ChecklistHasEvaluacionServiceImp repo;

	@GetMapping("/listar")
	public List<ChecklistHasEvaluacion> listar() {
		return repo.findAll();
	}

	@GetMapping("/findByCheckListEvaluacion/{idChecklist}/{idEvaluacion}")
	public List<ChecklistHasEvaluacion> findByCheckListEvaluacion(@PathVariable Long idChecklist,
			@PathVariable Long idEvaluacion) {
		return repo.findByCheckListEvaluacion(idChecklist, idEvaluacion);
	}

	@GetMapping("/findCheckListEvaluacionByCheckList/{idChecklist}")
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByCheckList(@PathVariable Long idChecklist) {
		return repo.findCheckListEvaluacionByCheckList(idChecklist);
	}

	@GetMapping("/findEvaluacionByCheckList/{idChecklist}")
	public List<Evaluacion> findEvaluacionByCheckList(@PathVariable Long idChecklist) {
		return repo.findEvaluacionByCheckList(idChecklist);
	}

	@GetMapping("/findCheckListEvaluacionByEvaluacion/{idEvaluacion}")
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluacion(@PathVariable Long idEvaluacion) {
		return repo.findCheckListEvaluacionByEvaluacion(idEvaluacion);
	}

	@GetMapping("/findCheckListByEvaluacion/{idEvaluacion}")
	public List<Checklist> findCheckListByEvaluacion(@PathVariable Long idEvaluacion) {
		return repo.findCheckListByEvaluacion(idEvaluacion);
	}

	@PostMapping("/findCheckListEvaluacionByEvaluaciones")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ChecklistHasEvaluacion> findCheckListEvaluacionByEvaluaciones(@RequestBody Collection<Long> expresion) {
		return repo.findCheckListEvaluacionByEvaluaciones(expresion);
	}

	@PostMapping("/findEvaluacionByIdsRol")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Evaluacion> findEvaluacionByIdsRol(@RequestBody Collection<Long> expresion) {
		return repo.findEvaluacionByIdsRol(expresion);
	}
	
	
	@PostMapping("/findEvaluacionByIdsChecklist")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Evaluacion> findEvaluacionByIdsChecklist(@RequestBody Collection<Long> expresion) {
		return repo.findEvaluacionByIdsChecklist(expresion);
	}
	
	
	
	
	
	@GetMapping("/ver/{id}")
	public ChecklistHasEvaluacion ver(@PathVariable ChecklistHasEvaluacionPK id) {
		return repo.findById(id);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public ChecklistHasEvaluacion crear(@RequestBody ChecklistHasEvaluacion entidad) {
		return repo.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ChecklistHasEvaluacion editar(@RequestBody ChecklistHasEvaluacion entidad,
			@PathVariable ChecklistHasEvaluacionPK id) {
		ChecklistHasEvaluacion entidadDb = repo.findById(id);
		entidadDb = entidad;

		return repo.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable ChecklistHasEvaluacionPK id) {
		repo.delete(id);
	}
}
