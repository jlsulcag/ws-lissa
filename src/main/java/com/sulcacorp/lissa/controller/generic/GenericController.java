package com.sulcacorp.lissa.controller.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.util.Constante;

public abstract class GenericController {

	protected List<Map<String, String>> formatMapMessageList(BindingResult result) {
		List<Map<String, String>> errors = 
				result.getFieldErrors().stream().map(err -> 
					{
						Map<String, String> error = new HashMap<>();
						error.put(err.getField(), err.getDefaultMessage());
						return error;
		}
		
	).collect(Collectors.toList());
		return errors;
	}
	
	/*HttpStatus : 200*/
	protected ResponseEntity<ResponseModel> getOkResponseConsulta(Object obj){
		ResponseModel response = ResponseModel.builder()
						.statusCode(HttpStatus.OK.value())
						.statusResponse(Constante.STATUS_SUCCESS)
						.data(obj)
						.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
	protected ResponseEntity<ResponseModel> getOkResponseRegistro(Object obj, BindingResult result){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.OK.value())
				.statusResponse(Constante.STATUS_SUCCESS)
				.errors(this.formatMapMessageList(result))
				.data(obj)
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
	/*HttpStatus : 201*/
	protected ResponseEntity<ResponseModel> getCreatedResponse(Object obj, BindingResult result){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.CREATED.value())
				.statusResponse(Constante.STATUS_SUCCESS)
				.errors(this.formatMapMessageList(result))
				.data(obj)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	/*HttpStatus : 201*/
	protected ResponseEntity<ResponseModel> getCreatedResponseTransactional(){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.CREATED.value())
				.statusResponse(Constante.STATUS_SUCCESS)
				.errors(null)
				.data(null)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	/*HttpStatus 400*/
	protected ResponseEntity<ResponseModel> getBadRequest(BindingResult result){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.statusResponse(Constante.STATUS_ERROR)
				.errors(this.formatMapMessageList(result))
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	/*HttpStatus : 404*/
	protected ResponseEntity<ResponseModel> getNotFoundRequest(){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.statusResponse(Constante.STATUS_ERROR)
				.errors(null)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	/*HttpStatus : 500*/
	protected ResponseEntity<ResponseModel> getInternalServerError(String msj){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.statusResponse(Constante.STATUS_ERROR)
				.errors(msj)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	/*HttpStatus : 500*/
	protected ResponseEntity<ResponseModel> getInternalServerErrorConstraintViolation(String msj){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.statusResponse(Constante.STATUS_WARNING)
				.errors(msj)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	protected ResponseEntity<ResponseModel> getResponseExists(){
		ResponseModel response = ResponseModel.builder()
				.statusCode(HttpStatus.ACCEPTED.value())
				.statusResponse(Constante.STATUS_EXISTS_REGISTRO)
				.errors(null)
				.data(null)
				.build();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
	
}
