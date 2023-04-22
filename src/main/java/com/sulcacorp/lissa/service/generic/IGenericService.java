package com.sulcacorp.lissa.service.generic;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, ID extends Serializable> {
	T save(T t) throws Exception;
	T update(T t) throws Exception;
	T findById(ID id) throws Exception;
	List<T> findAll() throws Exception;
	List<T> findAllAct() throws Exception;
	void delete(ID id) throws Exception;
	void deleteLogic(T t) throws Exception;
}
