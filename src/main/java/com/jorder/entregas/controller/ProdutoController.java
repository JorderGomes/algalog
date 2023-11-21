package com.jorder.entregas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.entregas.model.Produto;
import com.jorder.entregas.model.Vendedor;
import com.jorder.entregas.repository.ProdutoRepository;
import com.jorder.entregas.repository.VendedorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{vendedor_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> postProduto(@Valid @RequestBody Produto produto, @PathVariable Long vendedor_id) {
        Optional<Vendedor> optVendedor = vendedorRepository.findById(vendedor_id);
        if (optVendedor.isPresent()){
            produto.setVendedor(optVendedor.get());
            return ResponseEntity.ok(produtoRepository.save(produto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado com ID: " + vendedor_id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto, @PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/vendedor/{vendedorId}")
    public List<Produto> getProdutosByVendedor(@PathVariable Long vendedorId) {
        return produtoRepository.findByVendedorId(vendedorId);
    }

}
