package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.entity.EmpresaConvenio;
import com.sulcacorp.lissa.service.IEmpresaConvenioService;
import com.sulcacorp.lissa.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/empresa-convenio")
public class EmpresaConvenioController extends GenericController {

    @Autowired
    private IEmpresaConvenioService service;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> save(@Valid @RequestBody EmpresaConvenio empresaConvenio, BindingResult result){
        log.info("Inicio EmpresaConvenioController save ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(service.existsByDenominacion(StringUtils.upperCase(empresaConvenio.getDenominacion()))){
                return this.getResponseExists();
            }
            service.save(empresaConvenio);
            return this.getCreatedResponse(empresaConvenio, result);
        } catch (Exception e) {
            log.error("Error EmpresaConvenioController save ",e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@Valid @RequestBody EmpresaConvenio empresaConvenio, BindingResult result){
        log.info("Inicio EmpresaConvenioController update");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            EmpresaConvenio temp = service.findById(empresaConvenio.getIdEmpresaConvenio());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            service.update(empresaConvenio);
            return this.getOkResponseRegistro(empresaConvenio, result);
        } catch (Exception e){
            log.error("Error EmpresaConvenioController update ",e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findAll(){
        log.info("Inicio EmpresaConvenioController findAll");
        try {
            List<EmpresaConvenio> list = service.findAll();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        } catch (Exception e) {
            log.info("Error EmpresaConvenioController findAll",e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> findById(@PathVariable("id") Long id) {
        log.info("Inicio EmpresaConvenioController findById");
        try {
            EmpresaConvenio temp = service.findById(id);
            if(temp == null) {
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(temp);
        } catch (Exception e) {
            log.info("Error EmpresaConvenioController findById",e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }
}
