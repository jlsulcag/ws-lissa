package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.dto.OrganizacionDTO;
import com.sulcacorp.lissa.dto.ServicioDTO;
import com.sulcacorp.lissa.entity.Organizacion;
import com.sulcacorp.lissa.entity.Servicio;
import com.sulcacorp.lissa.repository.IServicioRepository;
import com.sulcacorp.lissa.service.IServicioService;
import com.sulcacorp.lissa.util.enums.EstadoEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private IServicioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServicioDTO save(ServicioDTO servicioDTO) throws Exception {
        Servicio servicio = new Servicio();
        servicioDTO.setDenominacion(servicioDTO.getDenominacion().toUpperCase());
        servicioDTO.setCodigo(servicioDTO.getCodigo().toUpperCase());

        servicio = repository.save(convertToEntity(servicioDTO));

        return convertToDTO(servicio);
    }

    @Override
    public ServicioDTO update(ServicioDTO servicioDTO) throws Exception {
        Servicio servicio = new Servicio();
        servicioDTO.setDenominacion(servicioDTO.getDenominacion().toUpperCase());
        servicioDTO.setCodigo(servicioDTO.getCodigo().toUpperCase());
        if(servicioDTO.getEstado() != null){
            servicioDTO.setEstado(servicioDTO.getEstado().equals(EstadoEnum.ACTIVO.getEstado())?EstadoEnum.ACTIVO.getEstado() : EstadoEnum.ANULADO.getEstado());
        }
        servicio = repository.save(convertToEntity(servicioDTO));

        return convertToDTO(servicio);
    }

    @Override
    public ServicioDTO findById(Long id) throws Exception {
        Optional<Servicio> op = repository.findById(id);
        return op.isPresent() ? convertToDTO(op.get()) : null;
    }

    @Override
    public List<ServicioDTO> findAll() throws Exception {
        List<Servicio> list = new ArrayList<>();
        List<ServicioDTO> listDTO = new ArrayList<>();
        list = repository.findAll(Sort.by(Sort.Direction.ASC, "denominacion"));
        if(!list.isEmpty()){
            list.forEach(obj -> listDTO.add(convertToDTO(obj)));
            return listDTO;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ServicioDTO> findAllAct() throws Exception {
        List<Servicio> list = new ArrayList<>();
        List<ServicioDTO> listDTO = new ArrayList<>();
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

    @Override
    public void deleteLogic(ServicioDTO servicioDTO) throws Exception {

    }

    private ServicioDTO convertToDTO(Servicio servicio){
        ServicioDTO servicioDTO = modelMapper.map(servicio, ServicioDTO.class);
        return servicioDTO;
    }

    private Servicio convertToEntity(ServicioDTO servicioDTO){
        Servicio servicio = modelMapper.map(servicioDTO, Servicio.class);
        return servicio;
    }

    @Override
    public boolean existsByDenominacion(String denominacion) throws Exception {
        return repository.existsByDenominacion(denominacion);
    }
}
