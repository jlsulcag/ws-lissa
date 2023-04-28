
package com.sulcacorp.lissa.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.data.annotation.Immutable;


@Data
@Immutable /*Evita el cambio en la BD*/
@Table(name = "VIEW_MEDICO")
@Entity
public class MedicoView {
	
	@Id
	@Column(name = "ID_MEDICO")
	private Long id;
	
	@Column(name = "FULLNAME")
	private String fullName;
	
	@Column(name = "NUMERO_DOCUMENTO_IDENTIDAD")
	private String numeroDocumentoIdentidad;
	
	@Column(name = "COLEGIATURA")
	private String colegiatura;
	
	@Column(name = "DESC_ESPECIALIDAD")
	private String especialidad;
	
	@Column(name = "DESC_TIPO_MEDICO")
	private String tipoMedico;
	
	@Column(name = "ESTADO")
	private String estado;
}
		 