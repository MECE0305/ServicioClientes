package com.cempresariales.servicio.clientes.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cempresariales.servicio.clientes.model.dto.*;
import com.cempresariales.servicio.commons.model.entity.Agencia;
import com.cempresariales.servicio.commons.model.entity.Empleado;
import com.cempresariales.servicio.commons.model.entity.RolHasEmpleado;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.RolDao;
import com.cempresariales.servicio.commons.model.entity.Rol;

@Service
public class RolServiceImp implements IRolService {

    @Autowired
    private RolDao rolDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Rol> findAll() {
        return rolDao.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    public void delete(Long id) {
        rolDao.deleteById(id);
    }

    @Override
    public List<Rol> findRolByArea(Long idArea) {
        return rolDao.findRolByArea(idArea);
    }

    @Override
    public List<Rol> findRolByEmpleados(Collection<Long> expresion) {
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
                    "select re.rol from RolHasEmpleado re where re.empleado.idEmpleado  in " + "(" + cadena + ") group by re.rol.nombreRol");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Rol> rolesByAgencias(Collection<Long> IdAgencias) {

        try {
            Iterator<Long> iterator = IdAgencias.iterator();
            String cadena = "";
            int x = 0;
            while (iterator.hasNext()) {

                cadena += iterator.next() + ",";
                if (x == IdAgencias.size() - 1) {
                    cadena = cadena.substring(0, cadena.length() - 1);
                }

                x++;
            }

            System.out.println("CADENA" + cadena);


            String queryStr = "SELECT r from Rol r where r.idRol in " +
                    "(SELECT re.rolHasEmpleadoPK.rolIdRol FROM RolHasEmpleado re " +
                    "WHERE re.rolHasEmpleadoPK.empleadoIdEmpleado in( " +
                    " select emp.idEmpleado from Empleado emp " +
                    " where emp.agenciaIdAgencia.idAgencia in (" + cadena + ")))";
            Query query = entityManager.createQuery(queryStr.toString());


            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public List<RolTopDTO> findTop10ByEmpresa(Long idEmpresa, String orderBy, int limit) {
        try {

            String order = "3";
            if (orderBy == null || orderBy.equals(""))
                order = "r.nombreRol";
            if (orderBy.equals("total"))
                order = "3";
            if (orderBy.equals("seccion"))
                order = "4";
            if (orderBy.equals("pregunta"))
                order = "5";

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.RolTopDTO(r.idRol, r.nombreRol, sum(eva.promedioTotal), sum(eva.promedioSeccion), sum(eva.promedioPregunta) )" +
                    " from Rol r" +
                    " join RolHasEmpleado re on re.rolHasEmpleadoPK.rolIdRol = r.idRol" +
                    " join Empleado em on em.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado" +
                    " join Agencia a on em.agenciaIdAgencia.idAgencia = a.idAgencia" +
                    " join Empresa emp on emp.idEmpresa = a.empresaIdEmpresa.idEmpresa" +
                    " join Checklist cl on cl.rolIdRol.idRol = r.idRol" +
                    " join ChecklistHasEvaluacion cle on cl.idChecklist = cle.checklistHasEvaluacionPK.checklistIdChecklist" +
                    " join Evaluacion eva on (eva.idEvaluacion = cle.checklistHasEvaluacionPK.evaluacionIdEvaluacion) and (eva.idEmpleado = em.idEmpleado)" +
                    " where emp.idEmpresa = ?1 group by r.nombreRol order by " + order + " desc";
            TypedQuery<RolTopDTO> consulta =
                    entityManager.createQuery(queryStr, RolTopDTO.class).setMaxResults(limit);
            consulta.setParameter(1, idEmpresa);
            List<RolTopDTO> results = consulta.getResultList();


            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<RolTopDTO>();
        }
    }

    @Override
    public List<Rol> findByEmpresa(Long idEmpresa) {

        try {

            String queryStr = "SELECT r from Agencia ag INNER JOIN" +
                    " Empleado emp ON ag.idAgencia = emp.agenciaIdAgencia.idAgencia INNER JOIN" +
                    " RolHasEmpleado re ON emp.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado AND " +
                    " emp.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado INNER JOIN" +
                    " Rol r ON re.rolHasEmpleadoPK.rolIdRol = r.idRol" +
                    " WHERE  (ag.empresaIdEmpresa.idEmpresa = "+idEmpresa+")" +
                    " GROUP BY r.idRol";

            Query query = entityManager.createQuery(queryStr.toString());
                        return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
