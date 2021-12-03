package com.sulcacorp.lissa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sulcacorp.lissa.dao.IMedicoViewRepository;
import com.sulcacorp.lissa.model.view.MedicoView;
import com.sulcacorp.lissa.service.IMedicoViewService;
import com.sulcacorp.lissa.service.exception.CustomServiceException;
import com.sulcacorp.lissa.util.Constant;

@Service
public class MedicoViewServiceImpl implements IMedicoViewService{
	
	@Autowired
	private IMedicoViewRepository repository;

	@Override
	public MedicoView findById(Long id) throws CustomServiceException {
		Optional<MedicoView> opt = repository.findById(id);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<MedicoView> findAllAct() throws CustomServiceException {
		return repository.findAllAct(Constant.STATUS_ENABLE);
	}


}
