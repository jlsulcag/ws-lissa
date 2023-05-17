package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.dto.ProcedimientoDTO;
import com.sulcacorp.lissa.service.IProcedimientoService;
import com.sulcacorp.lissa.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/procedimiento")
public class ProcedimientoController extends GenericController {

    @Autowired
    private IProcedimientoService service;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> save(@Valid @RequestBody ProcedimientoDTO procedimientoDTO, BindingResult result){
        log.info("Inicio ProcedimientoController save() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(service.existsByDenominacion(procedimientoDTO.getDenominacion().toUpperCase().trim())){
                return  this.getResponseExists();
            }
            procedimientoDTO = service.save(procedimientoDTO);
            return this.getCreatedResponse(procedimientoDTO, result);
        } catch (Exception e) {
            log.error("Error ProcedimientoController save() ", e.fillInStackTrace());
            return  this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@Valid @RequestBody ProcedimientoDTO procedimientoDTO, BindingResult result){
        log.info("Inicio ProcedimientoController update() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            ProcedimientoDTO temp = service.findById(procedimientoDTO.getIdProcedimiento());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            procedimientoDTO = service.update(procedimientoDTO);
            return this.getOkResponseRegistro(procedimientoDTO, result);
        } catch (Exception e) {
            log.error("Error ProcedimientoController update() ", e.fillInStackTrace());
            return  this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll(){
        log.info("Inicio ProcedimientoController findAll ");
        List<ProcedimientoDTO> list;
        try {
            list = service.findAll();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        } catch (Exception e) {
            log.error("Error ProcedimientoController findAll ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
        log.info("Inicio ProcedimientoController findById ");
        ProcedimientoDTO procedimientoDTO;
        try {
            procedimientoDTO = service.findById(id);
            if(procedimientoDTO == null){
                return  this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(procedimientoDTO);
        }catch (Exception e){
            log.error("Error ProcedimientoController findById ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

}
