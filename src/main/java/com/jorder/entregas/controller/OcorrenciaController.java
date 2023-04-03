package com.jorder.entregas.controller;

import com.jorder.entregas.mapper.OcorrenciaMapper;
import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.Ocorrencia;
import com.jorder.entregas.model.dto.OcorrenciaDto;
import com.jorder.entregas.model.input.OcorrenciaInput;
import com.jorder.entregas.service.impl.EntregaServiceImpl;
import com.jorder.entregas.service.impl.RegistroOcorrenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService ocorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;
    private EntregaServiceImpl entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDto registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService
                .registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaMapper.toOcorrenciaDto(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaDto> getOcorrencias(@PathVariable Long entregaId){
        Entrega entrega = entregaService.buscarEntrega(entregaId);
        return ocorrenciaMapper.toCollectionOcorrenciaDto(entrega.getOcorrencias());
    }

}
