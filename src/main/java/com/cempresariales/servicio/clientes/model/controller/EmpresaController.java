package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cempresariales.servicio.commons.model.entity.Empresa;
import com.cempresariales.servicio.clientes.model.service.EmpresaServiceImp;

@RestController
public class EmpresaController {

	@Autowired
	private EmpresaServiceImp empresaServicio;
	
	@GetMapping("/listarEmpresas")
	public List<Empresa> listarEmpresas(){
		return empresaServicio.findAll();
	}
	
	@GetMapping("/empresa/{id}")
	public Empresa ver(@PathVariable Long id){
		return empresaServicio.findById(id);
	}
	
	@PostMapping("/crearEmpresa/{empresa}")
	public Empresa crear(@PathVariable Empresa empresa){
		return empresaServicio.save(empresa);
	}
	
	@DeleteMapping("/eliminarEmpresa/{id}")
	public void eliminar(@PathVariable Long id){
		empresaServicio.deleteById(id);
	}
}
