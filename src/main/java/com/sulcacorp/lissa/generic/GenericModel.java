package com.sulcacorp.lissa.generic;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class GenericModel implements Serializable{
	
	private static final long serialVersionUID = -3216499732482401620L;
	private String estado = "1";

}
