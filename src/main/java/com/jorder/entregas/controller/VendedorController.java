package com.jorder.entregas.controller;

import java.util.List;

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

import com.jorder.entregas.model.Vendedor;
import com.jorder.entregas.repository.VendedorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    VendedorRepository vendedorRepository;

    @GetMapping
    public List<Vendedor> getVendedores() {
        return vendedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> getVendedor(@PathVariable Long id) {
        return vendedorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor postVendedor(@Valid @RequestBody Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        if (!vendedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> updateVendedor(@Valid @RequestBody Vendedor vendedor, @PathVariable Long id) {
        if (!vendedorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendedor.setId(id);
        vendedor = vendedorRepository.save(vendedor);
        return ResponseEntity.ok(vendedor);
    }

}
