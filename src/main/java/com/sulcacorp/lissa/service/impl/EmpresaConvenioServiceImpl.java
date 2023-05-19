package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.entity.EmpresaConvenio;
import com.sulcacorp.lissa.repository.IEmpresaConvenioRepository;
import com.sulcacorp.lissa.service.IEmpresaConvenioService;
import com.sulcacorp.lissa.util.enums.EstadoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaConvenioServiceImpl implements IEmpresaConvenioService {

    @Autowired
    private IEmpresaConvenioRepository repository;

    @Override
    public boolean existsByDenominacion(String denominacion) throws Exception {
        return repository.existsByDenominacion(denominacion);
    }

    @Override
    public EmpresaConvenio save(EmpresaConvenio empresaConvenio) throws Exception {
        empresaConvenio.setDenominacion(StringUtils.upperCase(empresaConvenio.getDenominacion()));
        return  repository.save(empresaConvenio);
    }

    @Override
    public EmpresaConvenio update(EmpresaConvenio empresaConvenio) throws Exception {
        empresaConvenio.setDenominacion(StringUtils.upperCase(empresaConvenio.getDenominacion()));
        return  repository.save(empresaConvenio);
    }

    @Override
    public EmpresaConvenio findById(Long id) throws Exception {
        Optional<EmpresaConvenio> op = repository.findById(id);
        return op.isPresent()?op.get():null;
    }

    @Override
    public List<EmpresaConvenio> findAll() throws Exception {
        List<EmpresaConvenio> list;
        list = repository.findAll(Sort.by(Sort.Direction.ASC, "denominacion"));
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<EmpresaConvenio> findAllAct() throws Exception {
        List<EmpresaConvenio> list;
        list = repository.findAllAct(EstadoEnum.ACTIVO.getEstado());
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public void deleteLogic(EmpresaConvenio empresaConvenio) throws Exception {

    }
}
