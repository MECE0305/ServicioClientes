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
import com.cempresariales.servicio.clientes.model.dto.AgenciasTopDTO;
import com.cempresariales.servicio.clientes.model.dto.AreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cempresariales.servicio.clientes.model.dao.AreaDao;
import com.cempresariales.servicio.commons.model.entity.Area;


@Service
public class AreaServiceImp implements AreaService {

    @Autowired
    private AreaDao rolDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Area> findAll() {
        return rolDao.findAll();
    }

    @Override
    public Area findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public Area save(Area rol) {
        return rolDao.save(rol);
    }

    @Override
    public void delete(Long id) {
        rolDao.deleteById(id);
    }

    @Override
    public List<Area> findAreasByRoles(Collection<Long> expresion) {
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
                    "select a from Area a where a.idArea  in (select p.areaIdArea.idArea from Rol p where p.idRol in (" + cadena + "))");

            Query query = entityManager.createQuery(queryString.toString());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<AreaDTO> findPromedioPorCategoriaArea(Long idEmpresa, Long idAgencia) {
        List<AreaDTO> resultado = new ArrayList<>();
        List<AreaDTO> resultadoTemp = new ArrayList<>();

        try {


            String queryEmpArea = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, ar.idArea, ar.nombreArea, COALESCE(round(avg (eva.puntajeEvaluacion),2),0.0), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1 and ag.idAgencia = ?2" +
                    " group by ar.nombreArea" +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> typedAG = entityManager.createQuery(queryEmpArea, AreaDTO.class);
            typedAG.setParameter(1, idEmpresa);
            typedAG.setParameter(2, idAgencia);

            resultadoTemp.addAll(typedAG.getResultList());

            for (AreaDTO area : resultadoTemp) {
                AreaDTO areaDTO = new AreaDTO("", area.getIdCategoria(), area.getNombreCategoria(), area.getIdCategoria(), "TOTALES", area.getPromedio(), area.getPeso());
                resultado.add(areaDTO);
            }


            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, pond.idCategoria, pond.nombreCategoria, COALESCE(round(avg (((pond.suma * 100) / pond.peso)),2), 0.0) , pond.peso)" +
                    " from RolHasEmpleado re JOIN " +
                    " Rol r ON re.rol.idRol = r.idRol JOIN" +
                    " Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Ponderadoporcategoria pond ON eva.idEvaluacion = pond.idEvaluacion JOIN" +
                    " Area ar ON ar.idArea = r.areaIdArea.idArea " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1  and ag.idAgencia = ?2" +
                    " group by r.nombreRol, pond.nombreCategoria" +
                    " ORDER BY r.nombreRol, pond.nombreCategoria ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idEmpresa);
            query.setParameter(2, idAgencia);

            resultado.addAll(query.getResultList());

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<AreaDTO> findPromedioPorCategoriaAreaZonaAgencia(Long idEmpresa, Long idAgencia, Long idEncabezado, Long idZona, Long idArea) {
        List<AreaDTO> resultado = new ArrayList<>();
        List<AreaDTO> resultadoTemp = new ArrayList<>();

        try {


            String queryEmpArea = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, r.idRol, r.nombreRol, COALESCE(round(avg (eva.puntajeEvaluacion),2),0.0), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1 and ag.idAgencia = ?2" +
                    " group by ar.nombreArea" +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> typedAG = entityManager.createQuery(queryEmpArea, AreaDTO.class);
            typedAG.setParameter(1, idEmpresa);
            typedAG.setParameter(2, idAgencia);

            resultadoTemp.addAll(typedAG.getResultList());

            for (AreaDTO area : resultadoTemp) {
                AreaDTO areaDTO = new AreaDTO("", area.getIdCategoria(), area.getNombreCategoria(), area.getIdCategoria(), "TOTALES", area.getPromedio(), area.getPeso());
                resultado.add(areaDTO);
            }


            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, pond.idCategoria, pond.nombreCategoria, COALESCE(round(avg (((pond.suma * 100) / pond.peso)),2), 0.0) , pond.peso)" +
                    " from RolHasEmpleado re JOIN " +
                    " Rol r ON re.rol.idRol = r.idRol JOIN" +
                    " Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
                    " EvaluacionHasEncabezado ehe ON ehe.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion = eva.idEvaluacion JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Ponderadoporcategoria pond ON eva.idEvaluacion = pond.idEvaluacion JOIN" +
                    " Area ar ON ar.idArea = r.areaIdArea.idArea " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1  and ag.idAgencia = ?2 and ar.idArea = ?3 and ag.idZonaEstructural = ?4 and ehe.evaluacionHasEncabezadoPK.encabezadoIdEncabezado = ?5  " +
                    " group by r.nombreRol, pond.nombreCategoria" +
                    " ORDER BY r.nombreRol, pond.nombreCategoria ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idEmpresa);
            query.setParameter(2, idAgencia);
            query.setParameter(3, idArea);
            query.setParameter(4, idZona);
            query.setParameter(5, idEncabezado);

            resultado.addAll(query.getResultList());

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public List<AreaDTO> findPromedioPorCategoriaAreaEmpresa(Long idEmpresa) {
        List<AreaDTO> resultado = new ArrayList<>();
        List<AreaDTO> resultadoTemp = new ArrayList<>();


        try {


            String queryEmpArea = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, ar.idArea, ar.nombreArea, COALESCE(round(avg (eva.puntajeEvaluacion),2), 0.0), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1 " +
                    " group by ar.nombreArea" +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> typedAG = entityManager.createQuery(queryEmpArea, AreaDTO.class);
            typedAG.setParameter(1, idEmpresa);

            resultadoTemp.addAll(typedAG.getResultList());

            for (AreaDTO area : resultadoTemp) {
                AreaDTO areaDTO = new AreaDTO("", area.getIdCategoria(), area.getNombreCategoria(), area.getIdCategoria(), "TOTALES", area.getPromedio(), area.getPeso());
                resultado.add(areaDTO);
            }


            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, pond.idCategoria, pond.nombreCategoria, COALESCE(round(avg (((pond.suma * 100) / pond.peso)),2), 0.0) , pond.peso)" +
                    " from RolHasEmpleado re JOIN " +
                    " Rol r ON re.rol.idRol = r.idRol JOIN" +
                    " Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Ponderadoporcategoria pond ON eva.idEvaluacion = pond.idEvaluacion JOIN" +
                    " Area ar ON ar.idArea = r.areaIdArea.idArea " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1  " +
                    " group by pond.nombreCategoria, ar.idArea" +
                    " ORDER BY ag.idAgencia, ar.idArea ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idEmpresa);


            resultado.addAll(query.getResultList());

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


    @Override
    public AreaDTO findPromedioPorCategoriaEmpresa(Long idEmpresa) {
        AreaDTO resultado = new AreaDTO();

        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ag.nombreAgencia, ar.idArea, ar.nombreArea, pond.idCategoria, pond.nombreCategoria, round( avg(pond.suma),2) , pond.peso)" +
                    " from RolHasEmpleado re JOIN " +
                    " Rol r ON re.rol.idRol = r.idRol JOIN" +
                    " Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Ponderadoporcategoria pond ON eva.idEvaluacion = pond.idEvaluacion JOIN" +
                    " Area ar ON ar.idArea = r.areaIdArea.idArea " +
                    " WHERE ag.empresaIdEmpresa.idEmpresa = ?1  " +
                    " ORDER BY ag.idAgencia, ar.idArea ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idEmpresa);


            resultado = query.getSingleResult();

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return new AreaDTO();
        }

    }

    @Override
    public List<Area> findByEmpresa(Long idEmpresa) {
        return rolDao.findAreaByEmpresa(idEmpresa);
    }

    @Override
    public List<AreaDTO> findPromedioPorAgenciaZonarol(Long idRol, Long idZona, Long idAgencia) {
        return null;
    }


    @Override
    public List<AreaDTO> findPromedioPorAgenciaArea(Long idEmpresa, Long idAgencia, Long idRol, Long idZonaE, Long idEncabezado, Integer posgroupBy) {
        try {

            System.out.println("idEmpresa: " + idEmpresa);
            System.out.println("idAgencia: " + idAgencia);
            System.out.println("idRol: " + idRol);
            System.out.println("idZona: " + idZonaE);
            System.out.println("idEncabezado: " + idEncabezado);
            String avgPuntaje = " round(avg(eva.puntajeEvaluacion),2) ";
            String groupBy = " GROUP BY em.idEmpresa, r.idRol, enc.evaluacionHasEncabezadoPK.encabezadoIdEncabezado, " +
                    "z.idZonaEstructural, ag.idAgencia ";
            if(idEmpresa !=0 && idAgencia ==0 && idRol ==0 && idZonaE ==0 && idEncabezado ==0){
                avgPuntaje = "round(eva.puntajeEvaluacion,2)";
                groupBy = " GROUP BY em.idEmpresa ";
            }
            if(posgroupBy == 2){
                groupBy = " GROUP BY em.idEmpresa, r.idRol ";
            }
            if(posgroupBy == 1){
                groupBy = " GROUP BY em.idEmpresa, r.idRol, enc.evaluacionHasEncabezadoPK.encabezadoIdEncabezado, " +
                        "z.idZonaEstructural, ag.idAgencia ";
            }




            StringBuilder queryStr = new StringBuilder("select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(" +
                    " em.nombreEmpresa, " +
                    " r.nombreRol," +
                    " ag.nombreAgencia, " +
                      avgPuntaje +")"+
                    " from Rol r" +
                    " JOIN RolHasEmpleado re ON re.rolHasEmpleadoPK.rolIdRol = r.idRol" +
                    " JOIN Empleado emp ON emp.idEmpleado = re.rolHasEmpleadoPK.empleadoIdEmpleado" +
                    " JOIN Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia" +
                    " JOIN Empresa em ON em.idEmpresa = ag.empresaIdEmpresa.idEmpresa" +
                    " JOIN ZonaEstructural z ON ag.zonaCiudad.zonaEstructuralIdZonaEstructural.idZonaEstructural = z.idZonaEstructural" +
                    " JOIN Evaluacion eva ON eva.idEmpleado = emp.idEmpleado" +
                    " JOIN EvaluacionHasEncabezado enc ON eva.idEvaluacion = enc.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion" +
                    " WHERE em.idEmpresa = :idEmpresa ");

            if (idAgencia != 0) {
                queryStr.append(" AND ag.idAgencia =  :idAgencia ");
            }
            if (idRol != 0) {
                queryStr.append(" and r.idRol = :idRol ");
            }
            if (idZonaE != 0) {
                queryStr.append(" AND z.idZonaEstructural = :idZona ");
            }
            if (idEncabezado != 0) {
                queryStr.append(" and enc.evaluacionHasEncabezadoPK.encabezadoIdEncabezado =  :idEncabezado ");
            }

            queryStr.append(groupBy);
            queryStr.append(" ORDER BY ag.nombreAgencia asc");


            System.out.println("CONSULTA: " + queryStr.toString());

            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr.toString(), AreaDTO.class).setMaxResults(10);
            query.setParameter("idEmpresa", idEmpresa);

            if (idAgencia != 0) {
                query.setParameter("idAgencia", idAgencia);
            }
            if (idRol != 0) {
                query.setParameter("idRol", idRol);
            }
            if (idZonaE != 0) {
                query.setParameter("idZona", idZonaE);
            }
            if (idEncabezado != 0) {
                query.setParameter("idEncabezado", idEncabezado);
            }

            System.out.println("queryStr: " + query.getResultList().size());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public List<AreaDTO> findPromedioAreaPorEmpresa(Long idEmpresa) {

        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(e.nombreEmpresa, e.idEmpresa, ar.nombreArea, ar.idArea, ar.nombreArea, round(avg (eva.puntajeEvaluacion),2), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Empresa e ON e.idEmpresa = ag.empresaIdEmpresa.idEmpresa JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
                    " WHERE e.idEmpresa = ?1 " +
                    " group by ar.nombreArea" +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idEmpresa);


            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    @Override
    public AreaDTO findPromedioPorAgencia(Long idAgencia) {

        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ar.nombreArea, ar.idArea, ag.nombreAgencia, ar.idArea, ar.nombreArea, avg (eva.puntajeEvaluacion), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado " +
                    " WHERE ag.idAgencia = ?1" +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idAgencia);


            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AreaDTO();
        }

    }

    @Override
    public AreaDTO findPromedioPorAgenciaAreaZonaEnca(Long idAgencia, Long idArea, Long idZonaE, Long idEncabezado) {
        try {

            String queryStr = "select new com.cempresariales.servicio.clientes.model.dto.AreaDTO(ar.nombreArea, ar.idArea, ag.nombreAgencia, ar.idArea, ar.nombreArea, avg (eva.puntajeEvaluacion), avg (eva.puntajeEvaluacion))" +
                    " from Area ar JOIN" +
                    " Rol r ON r.areaIdArea.idArea = ar.idArea JOIN " +
                    " RolHasEmpleado re ON re.rol.idRol = r.idRol JOIN " +
                    "Empleado emp ON re.empleado.idEmpleado = emp.idEmpleado JOIN " +
                    " Agencia ag ON emp.agenciaIdAgencia.idAgencia = ag.idAgencia JOIN" +
                    " Evaluacion eva ON emp.idEmpleado = eva.idEmpleado JOIN" +
                    " EvaluacionHasEncabezado ehe ON ehe.evaluacionHasEncabezadoPK.evaluacionIdEvaluacion = eva.idEvaluacion " +
                    " WHERE ag.idAgencia = ?1 and ar.idArea = ?2 and ag.idZonaEstructural = ?3 and ehe.evaluacionHasEncabezadoPK.encabezadoIdEncabezado = ?4 " +
                    " ORDER BY ar.nombreArea ";


            TypedQuery<AreaDTO> query = entityManager.createQuery(queryStr, AreaDTO.class);
            query.setParameter(1, idAgencia);
            query.setParameter(2, idArea);
            query.setParameter(3, idZonaE);
            query.setParameter(4, idEncabezado);


            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AreaDTO();
        }
    }

}
