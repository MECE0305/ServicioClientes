package com.cempresariales.servicio.clientes.model.controller;

import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.MedicionDTO;
import com.cempresariales.servicio.clientes.model.dto.RangoDTO;
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

import com.cempresariales.servicio.clientes.model.service.RangoServiceImp;
import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.OPTIONS })
@RequestMapping(value = "rango")
public class RangoController {

	@Autowired
	private RangoServiceImp service;

	@GetMapping("/listar")
	public List<RangoDesempenio> listar() {
		return service.findAll();
	}

	@GetMapping("/ver/{id}")
	public RangoDesempenio verItem(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/findByEmpresa/{idEmpresa}")
	public List<RangoDesempenio> findByEmpresa(@PathVariable Long idEmpresa) {
		return service.findByEmpresa(idEmpresa);
	}
	
	@GetMapping("/findByRangoAndEmpresa/{rango}/{idEmpresa}")
	public RangoDesempenio findByRangoAndEmpresa(@PathVariable Double rango, @PathVariable Long idEmpresa) {
		return service.findByRangoAndEmpresa(rango, idEmpresa);
	}

	@PostMapping(path = "/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public RangoDesempenio crear(@RequestBody RangoDesempenio entidad) {
		return service.save(entidad);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public RangoDesempenio editar(@RequestBody RangoDesempenio entidad, @PathVariable Long id) {
		RangoDesempenio entidadDb = service.findById(id);
		entidadDb = entidad;
		entidad.setIdRango(entidadDb.getIdRango());

		return service.save(entidadDb);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		service.deleteById(id);
	}

	@GetMapping("/findRangoByAgenciasDTO/{idEmpresa}/{agencias}/{estado}")
	public List<RangoDTO> findRangoByAgenciasDTO(@PathVariable Long idEmpresa, @PathVariable String agencias, @PathVariable Long estado) {
		return service.findRangoByAgenciasDTO(idEmpresa, agencias, estado);
	}

}
