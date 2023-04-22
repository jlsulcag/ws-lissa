package com.sulcacorp.lissa.service;

import com.sulcacorp.lissa.dto.MedicoDTO;
import com.sulcacorp.lissa.model.Medico;
import com.sulcacorp.lissa.service.generic.IGenericService;

public interface IMedicoService extends IGenericService<MedicoDTO, Long> {

	Medico saveCustom(MedicoDTO medicoDTO) throws Exception;

	Medico actualizardatos(MedicoDTO medico);

}
