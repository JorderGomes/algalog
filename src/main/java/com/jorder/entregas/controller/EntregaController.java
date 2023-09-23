package com.jorder.entregas.controller;

import com.jorder.entregas.mapper.EntregaMapper;
import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.dto.EntregaDto;
import com.jorder.entregas.model.input.EntregaInput;
import com.jorder.entregas.repository.EntregaRepository;
import com.jorder.entregas.service.impl.AtualizacaoStatusEntregaService;
import com.jorder.entregas.service.impl.EntregaServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaServiceImpl entregaService;
    private EntregaRepository entregaRepository;
    private EntregaMapper entregaMapper;
    private AtualizacaoStatusEntregaService statusEntregaService;

    @GetMapping
    public List<EntregaDto> getEntregas(){
        return entregaMapper.toCollectionEntregaDto(entregaRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntregaDto> getEntrega(@PathVariable Long id){
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toEntregaDto(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDto solicitarEntrega(@Valid @RequestBody EntregaInput entregaInput){
        Entrega novaEntrega = entregaMapper.toEntrega(entregaInput);
        Entrega entregaSolicitada = entregaService.solicitarEntrega(novaEntrega);
        return entregaMapper.toEntregaDto(entregaSolicitada);
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id){
        statusEntregaService.finalizar(id);
    }


}
