package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.AgenciasDTO;
import com.cempresariales.servicio.clientes.model.dto.EmpleadoDTO;
import com.cempresariales.servicio.clientes.model.dto.TiempoAtencionDTO;
import com.cempresariales.servicio.commons.model.entity.Rol;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.EmpleadoDao;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;

@Service
public class EmpleadoServiceImp implements IEmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;
    @Autowired
    private EmpleadoHasRolService empRolDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    public List<EmpleadoDTO> findAllDTO() {
        List<EmpleadoDTO> listRolEmp = empleadoDao.listarEmpleadosDTO();
        return listRolEmp;
    }

    @Override
    public Empleado findById(Long id) {
        try {
            Query query = entityManager.createQuery("select emp from Empleado emp where emp.idEmpleado = ?1");
            query.setParameter(1, id);
            return (Empleado) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new Empleado();
        }
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public void delete(Long id) {
        empleadoDao.deleteById(id);
    }

    @Override
    public List<Empleado> findByAgenciaIdAgencia(Agencia agencia) {
        try {
            long id = agencia.getIdAgencia();
            StringBuilder queryString = new StringBuilder(
                    "select emp from Empleado emp where emp.agenciaIdAgencia.idAgencia in "
                            + " (select ag.idAgencia from Agencia ag where ag.idAgencia in " + "(" + id + ")"
                            + ")");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Empleado> findEmpleadoByAgencias(Collection<Long> expresion) {
        try {

            Iterator<Long> iterator = expresion.iterator();
            String cadena = "";
            int x = 0;
            while (iterator.hasNext()) {

                cadena += iterator.next() + ",";
                if (x == expresion.size() - 1) {
                    cadena = cadena.substring(0, cadena.length() - 1);
                }

                x++;
            }

            StringBuilder queryString = new StringBuilder(
                    "select emp from Empleado emp where emp.agenciaIdAgencia.idAgencia in "
                            + " (select ag.idAgencia from Agencia ag where ag.idAgencia in " + "(" + cadena + ")"
                            + ")");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<TiempoAtencionDTO> findEmpleadoAreaTAtencion(Long idAgencia) {
        try {


            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.TiempoAtencionDTO(t.idEmpleado, t.nombreEmpleado, t.idArea, t.nombreArea , t.atencion, t.espera, t.permanencia)" +
                    " FROM  TiempoAtencionView t" +
                    " WHERE t.idAgencia = ?1 " +
                    " ORDER BY t.nombreArea ";


            TypedQuery<TiempoAtencionDTO> query = entityManager.createQuery(queryStr, TiempoAtencionDTO.class);

            query.setParameter(1, idAgencia);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<TiempoAtencionDTO>();
        }

    }

}
