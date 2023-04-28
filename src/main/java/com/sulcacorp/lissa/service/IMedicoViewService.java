package com.sulcacorp.lissa.service;

import java.util.List;
import com.sulcacorp.lissa.entity.view.MedicoView;

public interface IMedicoViewService{
	
	MedicoView findById(Long id) throws Exception;
	List<MedicoView> findAllAct() throws Exception;
	
}
