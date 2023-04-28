package com.sulcacorp.lissa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sulcacorp.lissa.entity.view.MedicoView;

@Repository
public interface IMedicoViewRepository extends JpaRepository<MedicoView, Long>{

	@Query("from MedicoView a where a.estado =:status")
	List<MedicoView> findAllAct(@Param("status") String status);
}
