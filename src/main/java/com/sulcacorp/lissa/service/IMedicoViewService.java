package com.sulcacorp.lissa.service;

import java.util.List;
import com.sulcacorp.lissa.model.view.MedicoView;
import com.sulcacorp.lissa.service.exception.CustomServiceException;

public interface IMedicoViewService{
	
	MedicoView findById(Long id) throws CustomServiceException;
	List<MedicoView> findAllAct() throws CustomServiceException;
	
}
