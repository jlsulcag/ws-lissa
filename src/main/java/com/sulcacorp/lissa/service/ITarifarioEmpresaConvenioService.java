package com.sulcacorp.lissa.service;

public interface ITarifarioEmpresaConvenioService{

    boolean existsByProcedimientoAndEmpresa(Long idProcedimiento, Long idEmpresa) throws Exception;

}
