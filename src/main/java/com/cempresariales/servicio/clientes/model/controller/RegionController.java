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

import com.cempresariales.servicio.commons.model.entity.Region;
import com.cempresariales.servicio.clientes.model.service.RegionServiceImp;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "region")
public class RegionController {

	@Autowired
	private RegionServiceImp regionServicio;
	
	@GetMapping("/listar")
	public List<Region> listarZonas(){
		return regionServicio.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Region ver(@PathVariable Long id){
		return regionServicio.findById(id);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)	
	public Region crear(@RequestBody Region region){
		return regionServicio.save(region);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Region editar(@RequestBody Region region, @PathVariable Long id) {
		Region regionDb = regionServicio.findById(id);
		
		regionDb.setNombreRegion(region.getNombreRegion());
		regionDb.setActivoRegion(region.getActivoRegion());
		regionDb.setRegionHasZonaList(region.getRegionHasZonaList());
				
        return regionServicio.save(regionDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id){
		regionServicio.delete(id);
	}
}
