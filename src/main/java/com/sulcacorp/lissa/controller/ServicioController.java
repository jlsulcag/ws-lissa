package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.dto.ServicioDTO;
import com.sulcacorp.lissa.service.IServicioService;
import com.sulcacorp.lissa.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/servicio")
public class ServicioController extends GenericController {

    @Autowired
    private IServicioService service;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> save(@Valid @RequestBody ServicioDTO servicioDTO, BindingResult result){
        log.info("Inicio ServicioController save() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(service.existsByDenominacion(servicioDTO.getDenominacion().toUpperCase())){
                return this.getResponseExists();
            }
            servicioDTO = service.save(servicioDTO);
            return this.getCreatedResponse(servicioDTO, result);
        } catch (Exception e) {
            log.error(">>> Error ServicioController save ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@Valid @RequestBody ServicioDTO servicioDTO, BindingResult result){
        log.info("Inicio ServicioController update() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }

        try {
            ServicioDTO temp = service.findById(servicioDTO.getIdServicio());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            servicioDTO = service.update(servicioDTO);
            return this.getOkResponseRegistro(servicioDTO, result);
        } catch (Exception e) {
            log.error(">>> Error ServicioController update ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll(){
        log.info("Inicio ServicioController findAll()");
        try {
            List<ServicioDTO> list = new ArrayList<>();
            list = service.findAll();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        }catch (Exception e){
            log.error("Error ServicioController findAll() ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @GetMapping(value = "/findAllAct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAllAct(){
        log.info("Inicio ServicioController findAllAct()");
        try {
            List<ServicioDTO> list = new ArrayList<>();
            list = service.findAllAct();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        }catch (Exception e){
            log.error("Error ServicioController findAllAct() ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
        log.info("Inicio ServicioController findById() ");
        ServicioDTO servicioDTO;

        try {
            servicioDTO = service.findById(id);
            if(servicioDTO == null){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(servicioDTO);
        } catch (Exception e) {
            log.error("Error ServicioController findById() {} ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

}
