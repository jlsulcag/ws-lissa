package com.sulcacorp.lissa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sulcacorp.lissa.entity.generic.GenericEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AREA")
public class Area extends GenericEntity {
	@Id
	@Column(name = "ID_AREA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArea;

	@Column(name = "AREA", nullable = false, length = 3)
	private String area;

}
