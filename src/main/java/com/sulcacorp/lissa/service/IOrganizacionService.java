package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.dto.OrganizacionDTO;
import com.sulcacorp.lissa.service.generic.IGenericService;


public interface IOrganizacionService extends IGenericService<OrganizacionDTO, Long> {

    boolean existsByRazonSocial(String razonSocail);
}
