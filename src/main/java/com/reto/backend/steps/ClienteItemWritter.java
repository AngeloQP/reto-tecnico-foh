package com.reto.backend.steps;

import com.reto.backend.entities.Cliente;
import com.reto.backend.service.ClienteService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClienteItemWritter implements ItemWriter<Cliente> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void write(List<? extends Cliente> list) throws Exception {
            clienteService.registrarCliente(list);
    }
}
