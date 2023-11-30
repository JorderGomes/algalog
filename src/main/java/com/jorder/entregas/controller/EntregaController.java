package com.jorder.entregas.controller;

import com.jorder.entregas.mapper.EntregaMapper;
import com.jorder.entregas.model.Entrega;
import com.jorder.entregas.model.ItemEntrega;
import com.jorder.entregas.model.Produto;
import com.jorder.entregas.model.StatusEntrega;
import com.jorder.entregas.model.dto.EntregaDto;
import com.jorder.entregas.model.input.EntregaInput;
import com.jorder.entregas.repository.EntregaRepository;
import com.jorder.entregas.repository.ItemEntregaRepository;
import com.jorder.entregas.repository.ProdutoRepository;
import com.jorder.entregas.service.impl.AtualizacaoStatusEntregaService;
import com.jorder.entregas.service.impl.EntregaServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaServiceImpl entregaService;
    private EntregaRepository entregaRepository;
    private EntregaMapper entregaMapper;
    private AtualizacaoStatusEntregaService statusEntregaService;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemEntregaRepository itemEntregaRepository;

    @GetMapping
    public List<EntregaDto> getEntregas() {
        return entregaMapper.toCollectionEntregaDto(entregaRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntregaDto> getEntrega(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toEntregaDto(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDto solicitarEntrega(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaMapper.toEntrega(entregaInput);
        Entrega entregaSolicitada = entregaService.solicitarEntrega(novaEntrega);
        return entregaMapper.toEntregaDto(entregaSolicitada);
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id) {
        statusEntregaService.finalizar(id);
    }

    @PutMapping("/{id}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> fechar(@PathVariable Long id){
        Optional<Entrega> optEntrega = entregaRepository.findById(id);

        if (!optEntrega.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (!optEntrega.get().getStatus().equals(StatusEntrega.ABERTA)) {
            return ResponseEntity.badRequest().body("Esta entrega já foi fechada.");
        }
        // optEntrega.get().fechar();
        statusEntregaService.fechar(optEntrega.get(), id);
        // entregaRepository.save(optEntrega.get());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("itens")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionarItemEntrega(
            @RequestParam Long id_entrega,
            @RequestParam Long id_produto,
            @RequestParam int qtde) {
        Optional<Entrega> optEntrega = entregaRepository.findById(id_entrega);
        Optional<Produto> optProduto = produtoRepository.findById(id_produto);

        if (!optEntrega.isPresent() || !optProduto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (!optEntrega.get().getStatus().equals(StatusEntrega.ABERTA)) {
            return ResponseEntity.badRequest().body("Esta entrega não está aberta a modificações.");
        }

        ItemEntrega novoItemEntrega = ItemEntrega.builder()
                .qtde(qtde)
                .produto(optProduto.get())
                .entrega(optEntrega.get())
                .valorUnitario(optProduto.get().getPreco())
                .build();

        novoItemEntrega = itemEntregaRepository.save(novoItemEntrega);
        return ResponseEntity.ok(novoItemEntrega);
    }

    @GetMapping("itens/{id_entrega}")
    public ResponseEntity<List<ItemEntrega>> getItensEntrega(@PathVariable Long id_entrega) {
        List<ItemEntrega> itensEntrega = itemEntregaRepository.findByEntregaId(id_entrega);
        return ResponseEntity.ok(itensEntrega);
    }

    @GetMapping("valor/{id_entrega}")
    public float getValorTotal(@PathVariable Long id_entrega) {
        Optional<Entrega> optEntrega = entregaRepository.findById(id_entrega);
        if (!optEntrega.isPresent()) {
            return (float) 0.0;
        }
        float valorItens = itemEntregaRepository.sumPrecoItens(id_entrega);
        return valorItens + Float.parseFloat(optEntrega.get().getTaxa().toString());
    }

    @DeleteMapping("itens")
    public ResponseEntity<?> deleteItemEntrega(@RequestParam Long id_entrega, @RequestParam Long id_item) {
        Optional<Entrega> optEntrega = entregaRepository.findById(id_entrega);

        if (!optEntrega.get().getStatus().equals(StatusEntrega.ABERTA)) {
            return ResponseEntity.badRequest().body("Esta entrega não está aberta a modificações.");
        }

        if (!itemEntregaRepository.existsById(id_item)) {
            return ResponseEntity.notFound().build();
        }
        itemEntregaRepository.deleteById(id_item);
        return ResponseEntity.noContent().build();
    }

}
