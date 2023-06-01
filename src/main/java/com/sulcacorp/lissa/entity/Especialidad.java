package com.sulcacorp.lissa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sulcacorp.lissa.entity.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ESPECIALIDAD")
public class Especialidad extends GenericEntity {
	
	@Id
	@Column(name = "ID_ESPECIALIDAD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidad;
	
	@Column(name = "DESC_ESPECIALIDAD", unique = true, nullable = false, length = 150)
	private String descEspecialidad;

}
