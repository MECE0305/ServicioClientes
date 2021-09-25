package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.clientes.model.dto.DetalleReporteBloquesDTO;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.AgenciaDao;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Service
public class AgenciaServiceImp implements IAgenciaService {

    @Autowired
    private AgenciaDao agenciaDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Agencia> findAll() {
        return agenciaDao.findAll();
    }

    @Override
    public Agencia findById(Long id) {
        return agenciaDao.findById(id).orElse(null);
    }

    @Override
    public Agencia save(Agencia agencia) {
        return agenciaDao.save(agencia);
    }

    @Override
    public void delete(Long id) {
        agenciaDao.deleteById(id);
    }

    @Override
    public List<Agencia> findByEmpresaIdEmpresa(Empresa empresa) {
        return agenciaDao.findByEmpresaIdEmpresa(empresa);
    }

    @Override
    public List<AgenciasTopDTO> findTop10ByEmpresa(Long idEmpresa, String orderBy, int limit) {
        try {

            String order = "3";
            if (orderBy == null || orderBy.equals(""))
                order = "ag.idAgencia";
            if (orderBy.equals("total"))
                order = "3";
            if (orderBy.equals("seccion"))
                order = "4";
            if (orderBy.equals("pregunta"))
                order = "5";

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO(ag.idAgencia, ag.nombreAgencia, sum(eva.promedioTotal), sum(eva.promedioSeccion), sum(eva.promedioPregunta) )" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado" +
                    " where ag.empresaIdEmpresa.idEmpresa = ?1 group by ag.nombreAgencia order by " + order + " desc";
            TypedQuery<AgenciasTopDTO> consulta =
                    entityManager.createQuery(queryStr, AgenciasTopDTO.class).setMaxResults(limit);
            consulta.setParameter(1, idEmpresa);
            List<AgenciasTopDTO> results = consulta.getResultList();


            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<AgenciasTopDTO>();
        }
    }
    @Override
    public List<AgenciasDTO> findPromedioPorAgencia(List<Long> IdAgencias, Long idEmpresa) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia, avg(eva.puntajeEvaluacion), ag.ceoAgencia)" +
                    " from Agencia ag " +
                    " join Empleado emp on emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " join Evaluacion eva on emp.idEmpleado = eva.idEmpleado" +
                    " where ag.idAgencia in :cadena and ag.empresaIdEmpresa.idEmpresa = ?1" +
                    " group by ag.nombreAgencia ";
            System.out.println("Sentencia : " +queryStr);
         //   Query query = entityManager.createQuery(queryStr.toString());

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class).setMaxResults(10);
            query.setParameter("cadena", IdAgencias);
            query.setParameter(1, idEmpresa);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


    }

    @Override
    public List<AgenciasDTO> findAgenciasPorRol(Long idEmpresa, Long idRol, List<Long> IdAgencias) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AgenciasDTO(ag.idAgencia, ag.nombreAgencia,  avg(ev.puntajeEvaluacion), r.nombreRol)"+
                    " from Empleado emp INNER JOIN" +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia INNER JOIN"+
                    " RolHasEmpleado re ON emp.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado INNER JOIN"+
                    " Rol r ON re.rolHasEmpleadoPK.rolIdRol = r.idRol INNER JOIN" +
                    " Evaluacion ev ON emp.idEmpleado = ev.idEmpleado" +
                    " WHERE  ag.empresaIdEmpresa.idEmpresa = ?1 and r.idRol = ?2 and ag.idAgencia in :cadena " +
                    " GROUP BY ag.nombreAgencia " +
                    " order by r.nombreRol, ag.nombreAgencia ";

            TypedQuery<AgenciasDTO> query = entityManager.createQuery(queryStr, AgenciasDTO.class).setMaxResults(10);
            query.setParameter(1, idEmpresa);
            query.setParameter(2, idRol);
            query.setParameter("cadena", IdAgencias);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
