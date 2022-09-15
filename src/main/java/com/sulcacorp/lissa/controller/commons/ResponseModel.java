package com.sulcacorp.lissa.controller.commons;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"timestamps", "statusCode", "statusResponse", "errors", "data"})
public class ResponseModel{
	
	@Builder.Default
	private LocalDateTime timestamps= LocalDateTime.now(); 
		
	//200,201,400
	private Integer statusCode;
	
	//1=Exito, 0=Error
	private Integer statusResponse;
	
	@JsonInclude(Include.NON_NULL)
	private Object errors; 
	
	@JsonInclude(Include.NON_NULL)
	private Object data;
}
