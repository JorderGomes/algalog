package com.jorder.entregas.service.impl;

import com.jorder.entregas.exception.NegocioException;
import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.Ocorrencia;
import com.jorder.entregas.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {

    EntregaServiceImpl entregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao){
        Entrega entrega = entregaService.buscarEntrega(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

}
