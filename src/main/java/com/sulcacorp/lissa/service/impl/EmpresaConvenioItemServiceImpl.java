package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.entity.EmpresaConvenioItem;
import com.sulcacorp.lissa.repository.IEmpresaConvenioItemRepository;
import com.sulcacorp.lissa.service.IEmpresaConvenioItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaConvenioItemServiceImpl implements IEmpresaConvenioItemService {

    @Autowired
    private IEmpresaConvenioItemRepository repository;

    @Override
    public EmpresaConvenioItem save(EmpresaConvenioItem empresaConvenioItem) throws Exception {
        empresaConvenioItem.setDenominacion(StringUtils.upperCase(empresaConvenioItem.getDenominacion()));
        return repository.save(empresaConvenioItem);
    }

    @Override
    public EmpresaConvenioItem update(EmpresaConvenioItem empresaConvenioItem) throws Exception {
        empresaConvenioItem.setDenominacion(StringUtils.upperCase(empresaConvenioItem.getDenominacion()));
        return repository.save(empresaConvenioItem);
    }

    @Override
    public EmpresaConvenioItem findById(Long aLong) throws Exception {
        Optional<EmpresaConvenioItem> op = repository.findById(aLong);
        return op.isPresent() ? op.get():null;
    }

    @Override
    public List<EmpresaConvenioItem> findAll() throws Exception {
        List<EmpresaConvenioItem> list = repository.findAll();
        if(list.isEmpty()){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<EmpresaConvenioItem> findAllAct() throws Exception {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public void deleteLogic(EmpresaConvenioItem empresaConvenioItem) throws Exception {

    }
}
