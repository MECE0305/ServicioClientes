package com.cempresariales.servicio.clientes.model.controller;

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

import com.cempresariales.servicio.commons.model.entity.Provincia;
import com.cempresariales.servicio.clientes.model.service.ProvinciaServiceImp;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "provincia")
public class ProvinciaController {

	@Autowired
	private ProvinciaServiceImp provinciaServicio;
	
	@GetMapping("/listar")
	public List<Provincia> listarProvincias(){
		return provinciaServicio.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Provincia verProvincia(@PathVariable Long id){
		return provinciaServicio.findById(id);
	}
	
	@PostMapping("/crear")
	public Provincia crear(@RequestBody Provincia provincia){
		return provinciaServicio.save(provincia);
	}
	
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Provincia editar(@RequestBody Provincia entidad, @PathVariable Long id) {
		Provincia entidadDb = provinciaServicio.findById(id);

		entidadDb.setNombreProvincia(entidad.getNombreProvincia());
		entidadDb.setActivoProvincia(entidad.getActivoProvincia());
		entidadDb.setZonaHasProvinciaList(entidad.getZonaHasProvinciaList());

		return provinciaServicio.save(entidadDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id){
		provinciaServicio.delete(id);
	}
}
