package com.cempresariales.servicio.clientes.model.controller;

import com.cempresariales.servicio.clientes.model.service.ZonaCiudadServiceImp;
import com.cempresariales.servicio.clientes.model.service.ZonaEstructuralServiceImpl;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructural;
import com.cempresariales.servicio.commons.model.entity.ZonaEstructuralHasCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.OPTIONS})
@RequestMapping(value = "zona-ciudad")
public class ZonaCiudadController {

    @Autowired
    private ZonaCiudadServiceImp repo;

    @GetMapping("/listar")
    public List<ZonaEstructuralHasCiudad> listar() {
        return repo.findAll();
    }

    @GetMapping("/ver/{id}")
    public ZonaEstructuralHasCiudad verItem(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping("/crear")
    public ZonaEstructuralHasCiudad crear(@RequestBody ZonaEstructuralHasCiudad entidad) {
        return repo.save(entidad);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ZonaEstructuralHasCiudad editar(@RequestBody ZonaEstructuralHasCiudad entidad, @PathVariable Long id) {
        return repo.save(entidad);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
