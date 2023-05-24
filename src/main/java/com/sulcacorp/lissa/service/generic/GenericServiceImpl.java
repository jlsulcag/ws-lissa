
package com.sulcacorp.lissa.service.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {


	public abstract JpaRepository<T, ID> getDao();
	
	@Override
	public T save(T t) throws Exception {
		return getDao().save(t);
	}

	@Override
	public T update(T t) throws Exception{
		return getDao().save(t);
	}

	@Override
	public T findById(ID id) throws Exception{
		Optional<T> opt = getDao().findById(id);
		return opt.isPresent()?opt.get(): null;
	}

	@Override
	public List<T> findAll() throws Exception{
		return getDao().findAll();
	}

	@Override
	public void delete(ID id) throws Exception{
		 getDao().deleteById(id);
		
	}
}

