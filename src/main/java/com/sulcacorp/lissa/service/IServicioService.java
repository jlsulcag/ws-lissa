package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.dto.ServicioDTO;
import com.sulcacorp.lissa.service.generic.IGenericService;
import org.springframework.stereotype.Service;

public interface IServicioService extends IGenericService<ServicioDTO, Long> {

    boolean existsByDenominacion(String denominacion) throws Exception;

}
