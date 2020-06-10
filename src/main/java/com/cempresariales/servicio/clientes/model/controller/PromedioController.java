package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.commons.model.entity.Promedio;
import com.cempresariales.servicio.clientes.model.service.PromedioServiceImp;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS })
@RequestMapping(value = "promedio")
public class PromedioController {

	@Autowired
	private PromedioServiceImp promedioServicio;
	
	@GetMapping("/listar")
	public List<Promedio> listarPromedios(){
		return promedioServicio.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Promedio verItem(@PathVariable Long id){
		return promedioServicio.findById(id);
	}
	
	@PostMapping("/crear")
	public Promedio crear(@RequestBody Promedio promedio){
		return promedioServicio.save(promedio);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id){
		promedioServicio.deleteById(id);
	}
}
