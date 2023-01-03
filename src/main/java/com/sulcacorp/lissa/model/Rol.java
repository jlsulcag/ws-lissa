package com.sulcacorp.lissa.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sulcacorp.lissa.model.generic.GenericModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROL")
public class Rol extends GenericModel{

	private static final long serialVersionUID = 8937561756526892800L;

	@Id
	@Column(name = "ID_ROL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;
	
	@Column(name = "NOMBRE_ROL", nullable = false, length = 30)
	private String nombreRol;
	
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDateTime fechaReg;
	
	
}
