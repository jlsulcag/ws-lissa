package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.controller.generic.IGenericController;
import com.sulcacorp.lissa.entity.EmpresaConvenioItem;
import com.sulcacorp.lissa.service.IEmpresaConvenioItemService;
import com.sulcacorp.lissa.util.Constante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/empresa-convenio-item")
public class EmpresaConvenioItemController extends GenericController implements IGenericController<EmpresaConvenioItem> {

    @Autowired
    private IEmpresaConvenioItemService service;

    @Override
    public ResponseEntity<ResponseModel> save(@Valid EmpresaConvenioItem empresaConvenioItem, BindingResult result) {
        log.info("Inicio EmpresaConvenioItemController save ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            return this.getCreatedResponse(service.save(empresaConvenioItem), result);
        } catch (Exception e){
            log.error("Error EmpresaConvenioItemController save ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> update(@Valid EmpresaConvenioItem empresaConvenioItem, BindingResult result) {
        log.info("Inicio EmpresaConvenioItemController update ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            EmpresaConvenioItem temp = service.findById(empresaConvenioItem.getIdEmpresaConvenioItem());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            return this.getCreatedResponse(service.save(empresaConvenioItem), result);
        } catch (Exception e){
            log.error("Error EmpresaConvenioItemController save ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findById(Long id) {
        log.info("Inicio EmpresaConvenioItemController findById");
        try {
            EmpresaConvenioItem temp = service.findById(id);
            if(temp == null){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(temp);
        } catch (Exception e){
            log.error("Error EmpresaConvenioItemController findById ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findAll() {
        log.info("Inicio EmpresaConvenioItemController findAll");
        try {
            List<EmpresaConvenioItem> list = service.findAll();
            if(list.isEmpty()) {
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        } catch (Exception e) {
            log.error(">>> Error execute findAll {}", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }
}
