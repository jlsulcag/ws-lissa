package com.sulcacorp.lissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sulcacorp.lissa.model.Medico;

@Repository
public interface IMedicoDAO extends JpaRepository<Medico, Long>{
	
	@Query("from MedicoView a where a.estado =:status")
	List<Medico> findAllAct(@Param("status") String status);

}
