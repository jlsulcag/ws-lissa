package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.entity.TipoIngreso;
import com.sulcacorp.lissa.service.generic.IGenericService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITipoIngresoService extends IGenericService<TipoIngreso, Long> {

    boolean existsByTipoIngreso(String tipoIngreso) throws Exception;
}
