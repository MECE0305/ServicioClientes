package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.MedicionDTO;
import com.cempresariales.servicio.clientes.model.dto.RangoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.RangoDao;
import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Service
public class RangoServiceImp implements RangoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RangoDao repo;

    @Override
    public List<RangoDesempenio> findAll() {
        return (List<RangoDesempenio>) repo.findAll();
    }

    @Override
    public RangoDesempenio findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public RangoDesempenio save(RangoDesempenio entidad) {
        return repo.save(entidad);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public RangoDesempenio findByRangoAndEmpresa(Double rango, Long idEmpresa) {
        return repo.findByRangoAndEmpresa(rango, idEmpresa);
    }

    @Override
    public List<RangoDesempenio> findByEmpresa(Long idEmpresa) {
        // TODO Auto-generated method stub
        return repo.findByEmpresa(idEmpresa);
    }


    @Override
    public List<RangoDTO> findRangoByAgenciasDTO(Long idEmpresa, String agencias, Long estado) {

        try {

            StringBuilder queryString = new StringBuilder("select new com.cempresariales.servicio.clientes.model.dto.RangoDTO(rd.nombreRango, rd.colorRango, count(rd.nombreRango) ) " +
                    " from RangoDesempenio rd" +
                    " join Empresa ep on ep.idEmpresa = rd.empresa.idEmpresa" +
                    " join Agencia ag on ag.empresaIdEmpresa.idEmpresa = ep.idEmpresa" +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join RolHasEmpleado re on re.rolHasEmpleadoPK.empleadoIdEmpleado = emp.idEmpleado" +
                    " join Rol rl on rl.idRol = re.rolHasEmpleadoPK.rolIdRol" +
                    " join Evaluacion e on e.idEmpleado = emp.idEmpleado" +
                    " join EstadoEvaluacion ee on ee.idEstado = e.estadoEvaluacionIdEstado.idEstado" +
                    " where e.puntajeEvaluacion between rd.minimoRango and rd.maximoRango" +
                    " and ag.idAgencia in " + "(" + agencias + ") ");


            if (idEmpresa == 0)
                queryString.append(" and ep.idEmpresa > 0");
            else queryString.append(" and ep.idEmpresa = " + idEmpresa);
            if (estado != 0)
                queryString.append(" and ee.idEstado = " + estado);


            queryString.append(" group by rd.nombreRango, rd.colorRango" +
                    " order by rd.nombreRango ");

            System.out.println("queryString: " + queryString.toString());

            TypedQuery<RangoDTO> consulta =
                    entityManager.createQuery(queryString.toString(), RangoDTO.class);
            List<RangoDTO> results = consulta.getResultList();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
