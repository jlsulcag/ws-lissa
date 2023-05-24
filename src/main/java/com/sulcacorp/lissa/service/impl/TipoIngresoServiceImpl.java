package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.entity.TipoIngreso;
import com.sulcacorp.lissa.repository.ITipoIngresoRepository;
import com.sulcacorp.lissa.service.ITipoIngresoService;
import com.sulcacorp.lissa.util.enums.EstadoEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TipoIngresoServiceImpl implements ITipoIngresoService {

    @Autowired
    private ITipoIngresoRepository repository;


    @Override
    public TipoIngreso save(TipoIngreso tipoIngreso) throws Exception {
        tipoIngreso.setTipoIngreso(StringUtils.upperCase(tipoIngreso.getTipoIngreso()));
        return repository.save(tipoIngreso);
    }

    @Override
    public TipoIngreso update(TipoIngreso tipoIngreso) throws Exception {
        tipoIngreso.setTipoIngreso(StringUtils.upperCase(tipoIngreso.getTipoIngreso()));
        return repository.save(tipoIngreso);
    }

    @Override
    public TipoIngreso findById(Long aLong) throws Exception {
        Optional<TipoIngreso> op = repository.findById(aLong);
        return op.isPresent() ? op.get():null;
    }

    @Override
    public List<TipoIngreso> findAll() throws Exception {
        List<TipoIngreso> list = repository.findAll();
        if(list.isEmpty())
            return Collections.emptyList();
        return list;
    }

    @Override
    public List<TipoIngreso> findAllAct() throws Exception {
        List<TipoIngreso> list = repository.findAllAct(EstadoEnum.ACTIVO.getEstado());
        if(list.isEmpty())
            return Collections.emptyList();
        return list;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public void deleteLogic(TipoIngreso tipoIngreso) throws Exception {

    }

    @Override
    public boolean existsByTipoIngreso(String tipoIngreso) throws Exception {
        return repository.existsByTipoIngreso(tipoIngreso);
    }
}
