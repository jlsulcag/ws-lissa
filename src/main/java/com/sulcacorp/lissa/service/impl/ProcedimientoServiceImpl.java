package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.dto.ProcedimientoDTO;
import com.sulcacorp.lissa.entity.Procedimiento;
import com.sulcacorp.lissa.repository.IProcedimientoRepository;
import com.sulcacorp.lissa.service.IProcedimientoService;
import com.sulcacorp.lissa.util.Constante;
import com.sulcacorp.lissa.util.enums.EstadoEnum;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedimientoServiceImpl implements IProcedimientoService {

    @Autowired
    private IProcedimientoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean existsByDenominacion(String denominacion) throws Exception {
        return repository.existsByDenominacion(denominacion);
    }

    @Override
    public ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO) throws Exception {
        Procedimiento procedimiento = new Procedimiento();
        procedimientoDTO.setCodigo(procedimientoDTO.getCodigo().toUpperCase());
        procedimientoDTO.setDenominacion(procedimientoDTO.getDenominacion().toUpperCase());
        procedimientoDTO.setEstado(Constante.STATUS_REG_ENABLE);
        procedimiento = repository.save(convertToEntity(procedimientoDTO));
        return convertToDTO(procedimiento);
    }

    @Override
    public ProcedimientoDTO update(ProcedimientoDTO procedimientoDTO) throws Exception {
        Procedimiento procedimiento = new Procedimiento();
        procedimientoDTO.setCodigo(procedimientoDTO.getCodigo().toUpperCase());
        procedimientoDTO.setDenominacion(procedimientoDTO.getDenominacion().toUpperCase());
        if(StringUtils.isNotBlank(procedimiento.getEstado())){
            procedimientoDTO.setEstado(StringUtils.equals(procedimiento.getEstado(), EstadoEnum.ACTIVO.getEstado())?
                    EstadoEnum.ACTIVO.getEstado():EstadoEnum.ANULADO.getEstado());
        }
        procedimiento = repository.save(convertToEntity(procedimientoDTO));
        return convertToDTO(procedimiento);
    }

    @Override
    public ProcedimientoDTO findById(Long id) throws Exception {
        Optional<Procedimiento> op = repository.findById(id);
        return op.isPresent()? convertToDTO(op.get()): null;
    }

    @Override
    public List<ProcedimientoDTO> findAll() throws Exception {
        List<Procedimiento> list = new ArrayList<>();
        List<ProcedimientoDTO> listDTO = new ArrayList<>();
        list = repository.findAll(Sort.by(Sort.Direction.ASC, "denominacion"));
        if(!list.isEmpty()){
            list.forEach(obj -> listDTO.add(convertToDTO(obj)));
            return listDTO;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ProcedimientoDTO> findAllAct() throws Exception {
        List<Procedimiento> list = new ArrayList<>();
        List<ProcedimientoDTO> listDTO = new ArrayList<>();
        list = repository.findAllAct(EstadoEnum.ACTIVO.getEstado());
        if(!list.isEmpty()){
            list.forEach(obj -> listDTO.add(convertToDTO(obj)));
            return listDTO;
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    private ProcedimientoDTO convertToDTO(Procedimiento procedimiento){
        ProcedimientoDTO procedimientoDTO = modelMapper.map(procedimiento, ProcedimientoDTO.class);
        return procedimientoDTO;
    }

    private Procedimiento convertToEntity(ProcedimientoDTO procedimientoDTO){
        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);
        return procedimiento;
    }

}
