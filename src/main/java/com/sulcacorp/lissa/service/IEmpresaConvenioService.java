package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.entity.EmpresaConvenio;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IEmpresaConvenioService extends IGenericService<EmpresaConvenio, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

}
