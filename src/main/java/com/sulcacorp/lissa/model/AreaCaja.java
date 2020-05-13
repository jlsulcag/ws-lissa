package com.sulcacorp.lissa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AREA_CAJA")
public class AreaCaja {
	@Id
	@Column(name = "ID_AREA_CAJA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAreaCaja;
	@Column(name = "AREA", nullable = false, length = 3)
	private String area;
	@Column(name = "ESTADO", nullable = false)
	private int estado;
}
