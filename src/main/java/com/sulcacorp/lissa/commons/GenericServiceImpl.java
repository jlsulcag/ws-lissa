package com.sulcacorp.lissa.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {


	public abstract JpaRepository<T, ID> getDao();
	
	@Override
	public T guardar(T t) {
		return getDao().save(t);
	}

	@Override
	public T actualizar(T t) {
		return getDao().save(t);
	}

	@Override
	public T buscar(ID id) {
		Optional<T> opt = getDao().findById(id);
		return opt.isPresent()?opt.get(): null;
	}

	@Override
	public List<T> listar() {
		return getDao().findAll();
	}

	@Override
	public void eliminar(ID id) {
		 getDao().deleteById(id);
		
	}
	

}
