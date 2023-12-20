package com.jorder.entregas.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jorder.entregas.exception.NegocioException;
import com.jorder.entregas.model.Cliente;
import com.jorder.entregas.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClienteServiceImplTest {

    @Autowired
    ClienteServiceImpl clienteServiceImpl;

    @Autowired
    ClienteRepository clienteRepository;

    Cliente cliente;
    Cliente clienteExistente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(null, "Maria Silva", "msilva2@email.com", "123456789");
    }

    @Test
    void mustSaveClientSuccessfully(){
        String randomEmailName = UUID.randomUUID().toString().substring(5);
        String randomEmail = randomEmailName + "@email.com";
        cliente.setEmail(randomEmail);
        Cliente clienteSalvo = clienteServiceImpl.createCliente(cliente);

        assertEquals(cliente, clienteSalvo);
    }

    @Test
    void mustNotSaveClientWhenEmailDuplicated(){
        final NegocioException e = assertThrows(NegocioException.class, () -> {
            clienteServiceImpl.createCliente(cliente);
        });

        assertEquals(e.getMessage(), "Já existe um cliente cadastrado com esse e-mail.");
        
    }

}
