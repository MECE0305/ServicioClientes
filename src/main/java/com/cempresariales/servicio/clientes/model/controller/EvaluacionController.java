package com.cempresariales.servicio.clientes.model.controller;

import java.util.Collection;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.*;
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

import com.cempresariales.servicio.commons.model.entity.Evaluacion;
import com.cempresariales.servicio.clientes.model.service.EvaluacionServiceImpl;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.OPTIONS})
@RequestMapping(value = "evaluacion")
public class EvaluacionController {

    @Autowired
    private EvaluacionServiceImpl repo;

    @GetMapping("/listar")
    public List<Evaluacion> listar() {
        return repo.findAll();
    }

    @PostMapping("/findByParams")
    public List<Evaluacion> findByParams(@RequestBody BuscadorDTO buscador) {
        return repo.findByParams(buscador);
    }

    @GetMapping("/findBySegmentacion/{idRegion}/{idZona}/{idProvincia}/{idCiudad}/{idZonaEstructural}")
    public List<Evaluacion> findBySegmentacion(@PathVariable Long idRegion, @PathVariable Long idZona,
                                               @PathVariable Long idProvincia, @PathVariable Long idCiudad, @PathVariable Long idZonaEstructural) {
        return repo.findBySegmentacion(idRegion, idZona, idProvincia, idCiudad, idZonaEstructural);
    }

    @GetMapping("/findByEvaluacionCategoria/{idEvaluacion}/{idCategoria}")
    public List<Evaluacion> findByEvaluacionCategoria(@PathVariable Long idEvaluacion, @PathVariable Long idCategoria) {
        return repo.findByEvaluacionCategoria(idEvaluacion, idCategoria);
    }


    @GetMapping("/findByFiltroTabClienteAndRol/{idCliente}/{idEmpresa}/{idSector}/{idAgencia}/{idEmpleado}/{idRol}/{idArea}")
    public List<Evaluacion> findByFiltroTabClienteAndRol(@PathVariable Long idCliente, @PathVariable Long idEmpresa,
                                                         @PathVariable Long idSector, @PathVariable Long idAgencia, @PathVariable Long idEmpleado,
                                                         @PathVariable Long idRol, @PathVariable Long idArea) {
        return repo.findByFiltroTabClienteAndRol(idCliente, idEmpresa, idSector, idAgencia, idEmpleado, idRol, idArea);
    }

    @GetMapping("/ver/{id}")
    public Evaluacion ver(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Evaluacion crear(@RequestBody Evaluacion entidad) {
        return repo.save(entidad);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Evaluacion editar(@RequestBody Evaluacion entidad, @PathVariable Long id) {
        Evaluacion entidadDb = repo.findById(id);
        entidadDb = entidad;

        return repo.save(entidadDb);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        repo.delete(id);
    }

    @PostMapping("/findEvaByAgencias")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Evaluacion> findEvaByAgencias(@RequestBody Collection<Long> expresion) {
        return repo.findEvaByAgencias(expresion);
    }

    @PostMapping("/reporteBloquesbyAgencias")
    @ResponseStatus(HttpStatus.CREATED)
    public String verItem(@RequestBody Collection<Long> idAgencias) {
        return repo.reporteBloquesbyAgencias(idAgencias);
    }

    @GetMapping("/detalleBloques/{idChecklist}/{idEvaluacion}")
    public List<DetalleReporteBloquesDTO> detalleBloquesbyAgencias(@PathVariable Long idChecklist, @PathVariable Long idEvaluacion) {
        return repo.detalleBloquesbyAgencias(idChecklist, idEvaluacion);
    }

    @GetMapping("/findEvaByAgenciasDTO/{idEmpresa}/{agencias}/{estado}/{activo}")
    public List<MedicionDTO> findEvaByAgenciasDTO(@PathVariable Long idEmpresa, @PathVariable String agencias,  @PathVariable Long estado, @PathVariable Boolean activo) {

        List<MedicionDTO> lista =  repo.findEvaByAgenciasDTO(idEmpresa, agencias, estado, activo);
        System.out.println("---------------"+lista.size());

        return lista;
    }

}
