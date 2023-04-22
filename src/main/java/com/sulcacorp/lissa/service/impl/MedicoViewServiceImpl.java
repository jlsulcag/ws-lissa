package com.sulcacorp.lissa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sulcacorp.lissa.model.view.MedicoView;
import com.sulcacorp.lissa.repository.IMedicoViewRepository;
import com.sulcacorp.lissa.service.IMedicoViewService;
import com.sulcacorp.lissa.util.Constante;

@Service
public class MedicoViewServiceImpl implements IMedicoViewService{
	
	@Autowired
	private IMedicoViewRepository repository;

	@Override
	public MedicoView findById(Long id) throws Exception {
		Optional<MedicoView> opt = repository.findById(id);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<MedicoView> findAllAct() throws Exception {
		return repository.findAllAct(Constante.STATUS_REG_ENABLE);
	}


}
