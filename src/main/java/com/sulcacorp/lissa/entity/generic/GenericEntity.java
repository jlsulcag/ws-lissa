package com.sulcacorp.lissa.entity.generic;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import com.sulcacorp.lissa.util.Constante;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Data
public class GenericEntity implements Serializable{
	
	private static final long serialVersionUID = -3216499732482401620L;
	
	@Size(min=1, max=1, message="El estado es requerido y debe ser 0 ó 1")
	@Column(name="ESTADO", nullable = false, length = 1, columnDefinition = "varchar(1) default '1'")
	private String estado = "1";

	/*
	* Para crear un valor predeterminado directamente en la definición de la tabla SQL , podemos usar la anotación @Column y establecer su parámetro columnDefinition
	* */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "FECHA_REG", nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime fechaReg;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "FECHA_ACT")
	private LocalDateTime fechaAct;

	@PrePersist
	void initCreatedAt() {
		setFechaReg(LocalDateTime.now());
		setEstado(Constante.STATUS_REG_ENABLE);
	}

	@PreUpdate
	void initUpdatedAt() {
		setFechaAct(LocalDateTime.now());
	}

}
