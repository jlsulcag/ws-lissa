package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.entity.TarifarioEmpresaConvenio;
import com.sulcacorp.lissa.repository.ITarifarioEmpresaConvenioRepository;
import com.sulcacorp.lissa.service.ITarifarioEmpresaConvenioService;
import com.sulcacorp.lissa.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifarioEmpresaConvenioServiceImpl extends GenericServiceImpl<TarifarioEmpresaConvenio, Long> implements ITarifarioEmpresaConvenioService{

    @Autowired
    private ITarifarioEmpresaConvenioRepository repository;

    @Override
    public JpaRepository<TarifarioEmpresaConvenio, Long> getDao() {
        return this.repository;
    }

    @Override
    public List<TarifarioEmpresaConvenio> findAllAct() throws Exception {
        return null;
    }

    @Override
    public boolean existsByProcedimientoAndEmpresa(Long idProcedimiento, Long idEmpresa) throws Exception {
        return repository.existsByProcedimientoAndEmpresa(idProcedimiento, idEmpresa);
    }
}
