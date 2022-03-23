package com.sulcacorp.lissa.controller.commons;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"timestamps", "statusCode", "code", "errors", "data"})
public class ResponseModel{
	
	@Builder.Default
	private LocalDateTime timestamps= LocalDateTime.now(); 
		
	private Integer statusCode;
	
	private String code;
	
	@JsonInclude(Include.NON_NULL)
	private Object errors; 
	
	@JsonInclude(Include.NON_NULL)
	private Object data;
}
