package com.sulcacorp.lissa.service.impl;

import com.sulcacorp.lissa.dto.EspecialidadDTO;
import com.sulcacorp.lissa.dto.OrganizacionDTO;
import com.sulcacorp.lissa.model.Especialidad;
import com.sulcacorp.lissa.model.Organizacion;
import com.sulcacorp.lissa.repository.IOrganizacionRepository;
import com.sulcacorp.lissa.service.IOrganizacionService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.enums.EstadoEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizacionServiceImpl implements IOrganizacionService {

    @Autowired
    private IOrganizacionRepository organizacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrganizacionDTO save(OrganizacionDTO organizacionDTO) throws CustomServiceException {
        Organizacion organizacion = new Organizacion();
        organizacionDTO.setRazonSocial(organizacionDTO.getRazonSocial().toUpperCase());
        organizacionDTO.setDireccion(organizacionDTO.getDireccion().toUpperCase());
        organizacionDTO.setImpuesto(organizacionDTO.getImpuesto().toUpperCase());
        organizacion = organizacionRepository.save(convertToEntity(organizacionDTO));
        return convertToDTO(organizacion);
    }

    @Override
    public OrganizacionDTO update(OrganizacionDTO organizacionDTO) throws CustomServiceException {
        Organizacion organizacion = new Organizacion();
        organizacionDTO.setRazonSocial(organizacionDTO.getRazonSocial().toUpperCase());
        organizacionDTO.setDireccion(organizacionDTO.getDireccion().toUpperCase());
        organizacionDTO.setImpuesto(organizacionDTO.getImpuesto().toUpperCase());
        organizacion = organizacionRepository.save(convertToEntity(organizacionDTO));
        return convertToDTO(organizacion);
    }

    @Override
    public OrganizacionDTO findById(Long id) throws CustomServiceException {
        Optional<Organizacion> op = organizacionRepository.findById(id);
        return op.isPresent() ? convertToDTO(op.get()) : null;
    }

    @Override
    public List<OrganizacionDTO> findAll() throws CustomServiceException {
        List<Organizacion> list = new ArrayList<>();
        List<OrganizacionDTO> listDTO = new ArrayList<>();
        list = organizacionRepository.findAll(Sort.by(Sort.Direction.ASC, "razonSocial"));
        if(!list.isEmpty()){
            list.forEach((obj)-> listDTO.add(convertToDTO(obj)));
            return listDTO;
        }
        return listDTO;
    }

    @Override
    public List<OrganizacionDTO> findAllAct() throws CustomServiceException {
        List<Organizacion> list = new ArrayList<>();
        List<OrganizacionDTO> listDTO = new ArrayList<>();
        list = organizacionRepository.findAllAct(EstadoEnum.ACTIVO.getEstado());
        if(!list.isEmpty()){
            list.forEach((obj)-> listDTO.add(convertToDTO(obj)));
            return listDTO;
        }
        return listDTO;
    }

    @Override
    public void delete(Long aLong) throws CustomServiceException {

    }

    @Override
    public void deleteLogic(OrganizacionDTO organizacion) throws CustomServiceException {

    }

    public boolean existsByRazonSocial(String razonSocial){
        return organizacionRepository.existsByRazonSocial(razonSocial);
    }

    private OrganizacionDTO convertToDTO(Organizacion organizacion){
        OrganizacionDTO organizacionDTO = modelMapper.map(organizacion, OrganizacionDTO.class);
        return organizacionDTO;
    }

    private Organizacion convertToEntity(OrganizacionDTO organizacionDTO){
        Organizacion organizacion = modelMapper.map(organizacionDTO, Organizacion.class);
        return organizacion;
    }
}
