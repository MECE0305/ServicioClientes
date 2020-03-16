package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.commons.model.entity.Cliente;
import com.cempresariales.servicio.clientes.model.service.ClienteServiceImp;

@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteServiceImp promedioServicio;
	
	@GetMapping("/listar")
	public List<Cliente> listarCliente(){
		return promedioServicio.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public Cliente verItem(@PathVariable Long id){
		return promedioServicio.findById(id);
	}
	
	
	@PostMapping(path = "/crear")
	@ResponseStatus(HttpStatus.CREATED)
	//@RequestMapping(value = "/crear", method = RequestMethod.POST, produces = "application/jsoncharset=UTF-8")
	public Cliente crear(@RequestBody Cliente cliente){
		return promedioServicio.save(cliente);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id){
		promedioServicio.deleteById(id);
	}
}
