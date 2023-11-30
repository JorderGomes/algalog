package com.jorder.entregas.service.impl;

import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.ItemEntrega;
import com.jorder.entregas.repository.EntregaRepository;
import com.jorder.entregas.repository.ItemEntregaRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AtualizacaoStatusEntregaService {

    private EntregaServiceImpl entregaService;
    private EntregaRepository entregaRepository;
    @Autowired
    private ItemEntregaRepository itemEntregaRepository;

    @Transactional
    public void finalizar(Long id) {
        Entrega entrega = entregaService.buscarEntrega(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }

    public void fechar(Entrega entrega, Long id) {
        List<ItemEntrega> itensEntrega = itemEntregaRepository.findByEntregaId(id);
        for (ItemEntrega item : itensEntrega){
            Optional<ItemEntrega> optItemBanco = itemEntregaRepository.findById(item.getId());
            if (optItemBanco.isPresent()) {
                int qtdeAtual = optItemBanco.get().getQtde();
                optItemBanco.get().setQtde(qtdeAtual - item.getQtde());
                // itemEntregaRepository.save(optItemBanco.get());
            }
        }
        entrega.fechar();
        entregaRepository.save(entrega);
    }
}
