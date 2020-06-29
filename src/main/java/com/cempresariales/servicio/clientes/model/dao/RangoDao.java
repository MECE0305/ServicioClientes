package com.cempresariales.servicio.clientes.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cempresariales.servicio.commons.model.entity.RangoDesempenio;

public interface RangoDao extends JpaRepository<RangoDesempenio, Long>{

}
