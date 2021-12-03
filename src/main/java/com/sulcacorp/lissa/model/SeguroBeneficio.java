package com.sulcacorp.lissa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SEGURO_BENEFICIO")
public class SeguroBeneficio {
	@Id
	@Column(name = "ID_SEGURO_BENEFICIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSeguroBeneficio;
	@ManyToOne
	@JoinColumn(name = "ID_SEGURO", nullable = false, foreignKey = @ForeignKey(name="FK_BENEFICIO_SEGURO"))
	private Seguro seguro;
	@Column(name = "DESCRIPCION", nullable = false, length = 120)
	private String descripcion;
	@Column(name = "PORCENTAJE_CUBIERTO", nullable = false)
	private int porcentajeCubierto;
	@Column(name = "ESTADO", nullable = false)
	private int estado;
}
