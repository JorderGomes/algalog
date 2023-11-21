package com.jorder.entregas.service.impl;

import com.jorder.entregas.exception.EntidadeNaoEncontradaException;
import com.jorder.entregas.model.Cliente;
import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.StatusEntrega;
import com.jorder.entregas.repository.EntregaRepository;
import com.jorder.entregas.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaServiceImpl {
    private ClienteService clienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitarEntrega(Entrega entrega){
        Cliente cliente = clienteService.getClienteById(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.ABERTA);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

    public Entrega buscarEntrega(Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }

}
