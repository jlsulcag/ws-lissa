package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.dto.ProcedimientoDTO;
import com.sulcacorp.lissa.entity.Procedimiento;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IProcedimientoService extends IGenericService<ProcedimientoDTO, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

}
