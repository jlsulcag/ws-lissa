package com.sulcacorp.lissa.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEDICO")
public class Medico {
	@Id
	@Column(name = "ID_MEDICO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ID_PERSONA", nullable = false, foreignKey = @ForeignKey(name="FK_MEDICO_PERSONA"))
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "ID_ESPECIALIDAD", nullable = false, foreignKey = @ForeignKey(name = "FK_ESPECIALIDAD_MEDICO"))
	private Especialidad especialidad;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_MEDICO", nullable = false, foreignKey = @ForeignKey(name = "FK_MEDICO_TIPO_MEDICO"))
	private TipoMedico tipoMedico;
	
	@Column(name = "COLEGIATURA", nullable = false, length = 20)
	private String colegiatura;
	
	//ISODATE 2020-10-01T05:00:00.000Z//Formato Universal de fechas
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "FECHA_REG", nullable = false)
	private LocalDate fechaReg;
	
	@Column(name = "ESTADO", nullable = false)
	private String estado;
}
