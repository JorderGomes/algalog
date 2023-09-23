package com.jorder.entregas.service.impl;

import com.jorder.entregas.exception.NegocioException;
import com.jorder.entregas.model.Cliente;
import com.jorder.entregas.repository.ClienteRepository;
import com.jorder.entregas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<Cliente> getClientes() {
        return null;
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        boolean emailInUse = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailInUse){
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
        }

        return  clienteRepository.save(cliente);
    }

    @Override
    public Cliente editCliente(Long id, Cliente cliente) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
