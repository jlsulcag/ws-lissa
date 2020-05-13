package com.sulcacorp.lissa.commons;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, ID extends Serializable> {
	T guardar(T t);
	T actualizar(T t);
	T buscar(ID id);
	List<T> listar();
	void eliminar(ID id);
}
