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

import com.cempresariales.servicio.commons.model.entity.ZonaEstructural;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;
import com.cempresariales.servicio.clientes.model.service.ZonaEstructuralServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "zona-estructural")
public class ZonaEstructuralController {

	@Autowired
	private ZonaEstructuralServiceImpl repo;

	@GetMapping("/listar")
	public List<ZonaEstructural> listarZonaEstructurales() {
		return repo.findAll();
	}

	@GetMapping("/findZonaEstructuralByCiudad/{id}")
	public List<ZonaEstructural> findZonaEstructuralByCiudad(@PathVariable Long id) {
		return repo.findZonaEstructuralByCiudad(id);
	}

	@GetMapping("/findZonaEstructuralCiudadByZonaEstructura/{id}")
	public List<ZonaEstructuralHasCiudad> findZonaEstructuralCiudadByZonaEstructura(@PathVariable Long id) {
		return repo.findZonaEstructuralCiudadByZonaEstructura(id);
	}

	@GetMapping("/ver/{id}")
	public ZonaEstructural verItem(@PathVariable Long id) {
		return repo.findById(id);
	}

	@PostMapping("/crear")
	public ZonaEstructural crear(@RequestBody ZonaEstructural entidad) {
		return repo.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ZonaEstructural editar(@RequestBody ZonaEstructural entidad, @PathVariable Long id) {
		ZonaEstructural entidadDb = repo.findById(id);

		entidadDb.setNombreZonaEstructural(entidad.getNombreZonaEstructural());
		entidadDb.setActivoZonaEstructural(entidad.getActivoZonaEstructural());
		entidadDb.setFechaActualizaZonaEstructural(entidad.getFechaActualizaZonaEstructural());
		entidadDb.setFechaCreacionZonaEstructural(entidad.getFechaCreacionZonaEstructural());
		entidadDb.setZonaEstructuralHasCiudadList(entidad.getZonaEstructuralHasCiudadList());

		return repo.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		repo.deleteById(id);
	}

}
