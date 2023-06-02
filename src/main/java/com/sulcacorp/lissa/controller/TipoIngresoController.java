package com.sulcacorp.lissa.controller;

import com.sulcacorp.lissa.controller.commons.ResponseModel;
import com.sulcacorp.lissa.controller.generic.GenericController;
import com.sulcacorp.lissa.controller.generic.IGenericController;
import com.sulcacorp.lissa.entity.TipoIngreso;
import com.sulcacorp.lissa.service.ITipoIngresoService;
import com.sulcacorp.lissa.util.Constante;
import com.sulcacorp.lissa.util.LogPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-ingreso")
public class TipoIngresoController extends GenericController implements IGenericController<TipoIngreso> {

    @Autowired
    private ITipoIngresoService service;

    @Override
    public ResponseEntity<ResponseModel> save(@Valid TipoIngreso tipoIngreso, BindingResult result) {
        LogPrint.logInicio(this, "save");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            if(service.existsByTipoIngreso(tipoIngreso.getTipoIngreso().toUpperCase())){
                return this.getResponseExists();
            }
            return this.getCreatedResponse(service.save(tipoIngreso), result);
        } catch (Exception e) {
            LogPrint.logError(this, "save", e);
            return this.getInternalServerError(Constante.ERROR_500);
        }

    }

    @Override
    public ResponseEntity<ResponseModel> update(@Valid TipoIngreso tipoIngreso, BindingResult result) {
        LogPrint.logInicio(this, "update");
        if(result.hasErrors()){
            return this.getBadRequest(result);
        }
        try {
            TipoIngreso temp = service.findById(tipoIngreso.getIdTipoIngreso());
            if(temp == null){
                return this.getNotFoundRequest();
            }
            return this.getCreatedResponse(service.save(tipoIngreso), result);
        } catch (Exception e) {
            LogPrint.logError(this, "update", e);
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findById(Long id) {
        LogPrint.logInicio(this, "findById");
        try {
            TipoIngreso obj = service.findById(id);
            if(obj == null){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(obj);
        } catch (Exception e) {
            LogPrint.logError(this, "findById ", e);
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> findAll() {
        LogPrint.logInicio(this, "findAll");
        try {
            List<TipoIngreso> list = service.findAll();
            if(list.isEmpty()){
                return this.getNotFoundRequest();
            }
            return this.getOkResponseConsulta(list);
        } catch (Exception e) {
            LogPrint.logError(this, "findAll ", e);
            return this.getInternalServerError(Constante.ERROR_500);
        }
    }
}
