package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.dto.OrganizacionDTO;
import com.sulcacorp.lissa.service.IOrganizacionService;
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
@RequestMapping("/api/organizacion")
public class OrganizacionController extends GenericController {

    @Autowired
    private IOrganizacionService organizacionService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll(){
        log.info("Inicio OrganizacionController findAll()");
        try {
            List<OrganizacionDTO> list = new ArrayList<>();
            list = organizacionService.findAll();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        }catch (Exception e){
            log.error("Error OrganizacionController findAll() ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @GetMapping(value = "/findAllAct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAllAct(){
        log.info("Inicio OrganizacionController findAllAct()");
        try {
            List<OrganizacionDTO> list = new ArrayList<>();
            list = organizacionService.findAllAct();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        }catch (Exception e){
            log.error("Error OrganizacionController findAllAct() ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id){
        log.info("Inicio OrganizacionController findById() ");
        OrganizacionDTO organizacion;

        try {
            organizacion = organizacionService.findById(id);
            if(organizacion == null){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(organizacion);
        } catch (Exception e) {
            log.error("Error OrganizacionController findById() {} ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> save(@Valid @RequestBody OrganizacionDTO organizacionDTO, BindingResult result){
        log.info("Inicio OrganizacionController save() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(organizacionService.existsByRazonSocial(organizacionDTO.getRazonSocial().toUpperCase())){
                return this.getResponseExists();
            }
            OrganizacionDTO organizacion = organizacionService.save(organizacionDTO);
            return this.getCreatedResponse(organizacion, result);
        } catch (Exception e) {
            log.error(">>> Error OrganizacionController save CustomServiceException : {}", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@Valid @RequestBody OrganizacionDTO organizacionDTO, BindingResult result){
        log.info("Inicio OrganizacionController update() ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            OrganizacionDTO temp = organizacionService.findById(organizacionDTO.getIdOrganizacion());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            organizacionDTO.setEstado(temp.getEstado());
            OrganizacionDTO organizacion = organizacionService.update(organizacionDTO);
            return this.getOkResponseRegistro(organizacion, result);
        } catch (Exception e) {
            log.error(">>> Error OrganizacionController update CustomServiceException : {}", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @PutMapping(value = "/disable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> disable(@PathVariable("id") Long id){
        log.info("Inicio OrganizacionController disable() ");
        OrganizacionDTO organizationDTO = null;
        try {
            organizationDTO = organizacionService.findById(id);
            if(organizationDTO == null){
                return this.getNotFoundRequest();
            }
            organizationDTO.setEstado(Constante.STATUS_REG_DISABLE);
            return this.getOkResponseConsulta(organizacionService.update(organizationDTO));

        } catch (Exception e) {
            log.error("Error OrganizacionController disable() CustomServiceException : {}", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }
}
