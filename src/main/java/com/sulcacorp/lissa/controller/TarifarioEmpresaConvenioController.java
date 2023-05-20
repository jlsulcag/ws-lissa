package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.controller.generic.IGenericController;
import com.sulcacorp.lissa.entity.TarifarioEmpresaConvenio;
import com.sulcacorp.lissa.service.ITarifarioEmpresaConvenioService;
import com.sulcacorp.lissa.service.impl.TarifarioEmpresaConvenioServiceImpl;
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
@RequestMapping("/api/tarifario")
public class TarifarioEmpresaConvenioController extends GenericController implements IGenericController<TarifarioEmpresaConvenio> {

    @Autowired
    private TarifarioEmpresaConvenioServiceImpl service;

    @Override
    public ResponseEntity<ResponseModel> save(@Valid TarifarioEmpresaConvenio tarifarioEmpresaConvenio, BindingResult result) {
        log.info("Inicio TarifarioEmpresaConvenioController save ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(service.existsByProcedimientoAndEmpresa(tarifarioEmpresaConvenio.getProcedimiento().getIdProcedimiento(), tarifarioEmpresaConvenio.getEmpresaConvenio().getIdEmpresaConvenio())){
                return this.getResponseExists();
            }
            return this.getCreatedResponse(service.save(tarifarioEmpresaConvenio), result);
        } catch (Exception e) {
            log.error("Error TarifarioEmpresaConvenioController save ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> update(@Valid TarifarioEmpresaConvenio tarifarioEmpresaConvenio, BindingResult result) {
        log.info("Inicio TarifarioEmpresaConvenioController update ");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            TarifarioEmpresaConvenio temp = service.findById(tarifarioEmpresaConvenio.getIdTarifarioEmpresaConvenio());
            if(temp == null){
                return this.getBadRequest(result);
            }
            return this.getCreatedResponse(service.save(tarifarioEmpresaConvenio), result);
        } catch (Exception e) {
            log.error("Error TarifarioEmpresaConvenioController update ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findById(Long id) {
        log.info("Inicio TarifarioEmpresaConvenioController findById ");
        try {
            TarifarioEmpresaConvenio obj = service.findById(id);
            if(obj == null) {
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(obj);
        } catch (Exception e) {
            log.error("Error TarifarioEmpresaConvenioController findById ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findAll() {
        log.info("Inicio TarifarioEmpresaConvenioController findAll ");
        try {
            List<TarifarioEmpresaConvenio> list = service.findAll();
            if(list.isEmpty()) {
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        } catch (Exception e) {
            log.error("Error TarifarioEmpresaConvenioController findAll ", e.fillInStackTrace());
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }
}
