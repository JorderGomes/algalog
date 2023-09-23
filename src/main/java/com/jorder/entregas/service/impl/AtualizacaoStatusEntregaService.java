package com.jorder.entregas.service.impl;

import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AtualizacaoStatusEntregaService {

    private EntregaServiceImpl entregaService;
    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long id){
        Entrega entrega = entregaService.buscarEntrega(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }


}



